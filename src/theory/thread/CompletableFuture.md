Okay, let's dive into `CompletableFuture` in Java. It's a powerful class introduced in Java 8 for managing asynchronous
computations in a non-blocking, composable way. Think of it as an evolution of the older `Future` interface, adding much
more flexibility.

---

## What is `CompletableFuture`?

At its core, a `CompletableFuture<T>` represents a **future result** of an asynchronous operation – an operation that
runs in the background, potentially on a different thread, without blocking the main thread. The `<T>` indicates the
type of the result the operation will eventually produce.

It implements both the `Future<T>` and `CompletionStage<T>` interfaces. While it has the basic methods of `Future` (like
`get()`, `isDone()`, `cancel()`), its real power comes from the `CompletionStage` interface, which enables:

1. **Asynchronous Execution:** Running tasks without blocking the caller thread.
2. **Composability:** Chaining multiple asynchronous tasks together.
3. **Combining Futures:** Merging results from several independent futures.
4. **Exception Handling:** Defining how to handle errors that occur during asynchronous execution.
5. **Notifications:** Triggering actions upon completion (either successful or exceptional).

---

## Why `CompletableFuture` over `Future`?

The original `java.util.concurrent.Future` interface (pre-Java 8) had limitations:

* **Blocking `get()`:** Calling `future.get()` blocks the current thread until the result is available. This defeats the
  purpose of non-blocking asynchronous programming if not used carefully.
* **No Chaining:** You couldn't easily say "when this future completes, *then* do this next asynchronous thing with the
  result." You'd typically have to block with `get()` and then start the next task.
* **Limited Exception Handling:** Exception handling usually involved wrapping `get()` in a `try-catch` block.
* **Manual Completion:** You couldn't manually complete a `Future` (e.g., setting its value or exception from outside
  the task itself).

`CompletableFuture` addresses all these issues.

---

## Creating `CompletableFuture` Instances

You typically create `CompletableFuture` instances using static factory methods:

1. **`CompletableFuture.supplyAsync(Supplier<U> supplier)`:**
    * Runs a task asynchronously that **returns a value**.
    * The `Supplier` provides the logic to compute the value.
    * Uses the common `ForkJoinPool` by default.
    * Example: `CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> fetchUserData(userId));`

2. **`CompletableFuture.supplyAsync(Supplier<U> supplier, Executor executor)`:**
    * Same as above, but you specify the `Executor` (thread pool) to run the task on.
    * Example:
      `CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> fetchUserData(userId), myCustomExecutor);`

3. **`CompletableFuture.runAsync(Runnable runnable)`:**
    * Runs a task asynchronously that **does not return a value** (`CompletableFuture<Void>`).
    * The `Runnable` contains the task logic.
    * Uses the common `ForkJoinPool` by default.
    * Example: `CompletableFuture<Void> future = CompletableFuture.runAsync(() -> logUserAction(userId));`

4. **`CompletableFuture.runAsync(Runnable runnable, Executor executor)`:**
    * Same as above, but with a specific `Executor`.
    * Example:
      `CompletableFuture<Void> future = CompletableFuture.runAsync(() -> logUserAction(userId), myCustomExecutor);`

5. **`CompletableFuture.completedFuture(U value)`:**
    * Creates an already completed `CompletableFuture` with the given value. Useful for starting chains or in testing.
    * Example: `CompletableFuture<String> future = CompletableFuture.completedFuture("Default Value");`

---

## Chaining Operations (Composition)

This is where `CompletableFuture` shines. You can define dependent actions that execute when a previous stage completes.

* **Processing the Result (`thenApply`, `thenAccept`, `thenRun`)**

    * **`thenApply(Function<? super T,? extends U> fn)`:** Transforms the result. Takes the result of the previous
      stage (`T`), applies the function `fn`, and returns a *new* `CompletableFuture` with the transformed result (`U`).
      Like `map` in streams.
        ```java
        CompletableFuture<String> dataFuture = CompletableFuture.supplyAsync(this::fetchData);
        CompletableFuture<Integer> lengthFuture = dataFuture.thenApply(data -> data.length());
        // or: dataFuture.thenApply(String::length);
        ```
    * **`thenAccept(Consumer<? super T> action)`:** Consumes the result. Takes the result (`T`) and performs an action
      with it. Returns `CompletableFuture<Void>`. Like `forEach` in streams.
        ```java
        CompletableFuture<String> dataFuture = CompletableFuture.supplyAsync(this::fetchData);
        CompletableFuture<Void> processingFuture = dataFuture.thenAccept(data -> System.out.println("Data: " + data));
        ```
    * **`thenRun(Runnable action)`:** Executes an action *after* the previous stage completes, but doesn't use its
      result. Returns `CompletableFuture<Void>`.
        ```java
        CompletableFuture<String> dataFuture = CompletableFuture.supplyAsync(this::fetchData);
        CompletableFuture<Void> completionFuture = dataFuture.thenRun(() -> System.out.println("Fetching complete."));
        ```

* **Asynchronous Chaining (`thenCompose`)**

    * **`thenCompose(Function<? super T,? extends CompletionStage<U>> fn)`:** Chains a dependent *asynchronous*
      operation. If your next step itself returns a `CompletableFuture`, use `thenCompose`. It takes the result (`T`) of
      the current stage, applies a function `fn` that *returns another `CompletableFuture<U>`*, and the result is that
      *inner* `CompletableFuture`. This avoids nested futures like `CompletableFuture<CompletableFuture<U>>`. It's like
      `flatMap` in streams.

        ```java
        CompletableFuture<UserId> userIdFuture = CompletableFuture.supplyAsync(this::getUserId);

        // If getUserDetails also returns a CompletableFuture<UserDetails>
        CompletableFuture<UserDetails> userDetailsFuture = userIdFuture.thenCompose(userId -> getUserDetailsAsync(userId));
        ```
      *Contrast with `thenApply`*: If `getUserDetailsAsync` returned a `CompletableFuture` and you used `thenApply`,
      you'd get `CompletableFuture<CompletableFuture<UserDetails>>`. `thenCompose` flattens this.

* **Async Variants (`*Async`)**

    * Most chaining methods have `*Async` counterparts (e.g., `thenApplyAsync`, `thenAcceptAsync`, `thenRunAsync`,
      `thenComposeAsync`).
    * These variants allow you to specify an `Executor` for the chained task or default to the
      `ForkJoinPool.commonPool()`. This controls *which thread pool* the subsequent task runs on.
    * The non-`Async` versions *might* run the task in the same thread as the previous stage or in the calling thread,
      depending on whether the previous stage was already complete. Use `*Async` for more predictable execution context
      management.

    ```java
    // Run the transformation logic on a specific executor
    CompletableFuture<Integer> lengthFuture = dataFuture.thenApplyAsync(String::length, myComputationExecutor);
    ```

---

## Combining Multiple Futures

You can coordinate multiple independent `CompletableFuture` instances.

* **`thenCombine(CompletionStage<? extends U> other, BiFunction<? super T,? super U,? extends V> fn)`:**
    * Waits for *both* this future (`CompletableFuture<T>`) and the `other` future (`CompletableFuture<U>`) to complete.
    * Combines their results using the `BiFunction` `fn`.
    * Returns a `CompletableFuture<V>` containing the combined result.
    ```java
    CompletableFuture<Double> weightFuture = CompletableFuture.supplyAsync(this::fetchWeightKg);
    CompletableFuture<Double> heightFuture = CompletableFuture.supplyAsync(this::fetchHeightCm);

    CompletableFuture<Double> bmiFuture = weightFuture.thenCombine(heightFuture, (weight, height) -> {
        double heightM = height / 100.0;
        return weight / (heightM * heightM);
    });
    ```

* **`thenAcceptBoth(CompletionStage<? extends U> other, BiConsumer<? super T,? super U> action)`:**
    * Waits for both futures to complete.
    * Performs an action using both results.
    * Returns `CompletableFuture<Void>`.

* **`runAfterBoth(CompletionStage<?> other, Runnable action)`:**
    * Waits for both futures to complete.
    * Runs an action that doesn't depend on the results.
    * Returns `CompletableFuture<Void>`.

* **Waiting for Any/All:**

    * **`CompletableFuture.allOf(CompletableFuture<?>... cfs)`:** Returns a `CompletableFuture<Void>` that completes
      only when *all* of the provided futures have completed (successfully or exceptionally). Useful for waiting for a
      group of tasks. To get results, you often need to join/process the original futures *after* `allOf` completes.
        ```java
        CompletableFuture<Void> allDone = CompletableFuture.allOf(future1, future2, future3);
        allDone.thenRun(() -> {
            // Now it's safe to get results from future1, future2, future3 (potentially using join())
            System.out.println("All tasks finished.");
        });
        ```
    * **`CompletableFuture.anyOf(CompletableFuture<?>... cfs)`:** Returns a `CompletableFuture<Object>` that completes
      as soon as *any* of the provided futures completes. The result is the result of the first completed future.

---

## Exception Handling

`CompletableFuture` provides elegant ways to handle exceptions that might occur in any stage of the chain.

* **`exceptionally(Function<Throwable, ? extends T> fn)`:**
    * Handles exceptions from the preceding stages.
    * If the preceding stage completed exceptionally, the `Function` `fn` is called with the `Throwable`. It can return
      a substitute result (`T`) allowing the chain to continue successfully.
    * If the preceding stage completed normally, this stage is skipped.
    ```java
    CompletableFuture<String> dataFuture = CompletableFuture.supplyAsync(this::fetchDataWhichMightFail)
        .exceptionally(ex -> {
            System.err.println("Failed to fetch data: " + ex.getMessage());
            return "Default Data"; // Provide a default value
        });
    ```

* **`handle(BiFunction<? super T, Throwable, ? extends U> fn)`:**
    * Handles *both* normal completion and exceptional completion.
    * The `BiFunction` `fn` receives two arguments: the result (`T`, or `null` if an exception occurred) and the
      exception (`Throwable`, or `null` if successful).
    * It must produce a result (`U`), allowing transformation or recovery regardless of the outcome.
    ```java
    CompletableFuture<Integer> processedResult = CompletableFuture.supplyAsync(this::riskyOperation)
        .handle((result, ex) -> {
            if (ex != null) {
                System.err.println("Operation failed: " + ex.getMessage());
                return -1; // Return error code
            } else {
                return processResult(result); // Process normal result
            }
        });
    ```

---

## Getting the Result (Blocking)

While the goal is often non-blocking chains, sometimes the main thread (or another part of your synchronous code) needs
the final result.

* **`T get()`:** Waits indefinitely if necessary for the future to complete and returns its result. Throws checked
  exceptions (`ExecutionException` wrapping the actual cause, `InterruptedException`). **Blocks the calling thread.**
* **`T get(long timeout, TimeUnit unit)`:** Waits for the specified time. Throws `TimeoutException` if the future
  doesn't complete within the timeout. Also throws checked exceptions. **Blocks the calling thread.**
* **`T join()`:** Similar to `get()`, but throws unchecked exceptions (`CompletionException` wrapping the actual cause,
  potentially `CancellationException`). Often preferred in functional chains or lambda expressions where checked
  exceptions are cumbersome. **Blocks the calling thread.**

**Caution:** Use `get()` and `join()` sparingly, typically only when bridging back from asynchronous to synchronous
code, often at the very end of your application's logic or in tests. Overusing them negates the benefits of asynchronous
programming.

---

## Manual Completion

You can create a `CompletableFuture` and complete it later, which is useful for integrating with APIs that use
callbacks.

* **`complete(T value)`:** If not already completed, sets the result to the given value and triggers dependent stages.
  Returns `true` if it completed it, `false` if already completed.
* **`completeExceptionally(Throwable ex)`:** If not already completed, sets the outcome to an exception and triggers
  dependent stages.
* **`cancel(boolean mayInterruptIfRunning)`:** Attempts to cancel the execution.

```java
// Example: Adapting a callback API
CompletableFuture<ApiResponse> future = new CompletableFuture<>();

thirdPartyApi.

makeRequest(request, response ->{ // Callback interface
        if(response.

isSuccess()){
        future.

complete(response.getData());
        }else{
        future.

completeExceptionally(response.getError());
        }
        });

// Now you can chain operations onto 'future'
        future.

thenAccept(data ->System.out.

println("Got data: "+data))
        .

exceptionally(ex ->{System.err.

println("API call failed"); return null;});
```

---

In summary, `CompletableFuture` is a versatile tool for writing sophisticated, non-blocking, and readable asynchronous
code in Java by allowing easy creation, composition, combination, and error handling of asynchronous tasks.