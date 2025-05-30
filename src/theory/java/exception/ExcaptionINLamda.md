Exception handling in Java lambdas can be tricky, especially with **checked exceptions**, because functional interfaces like `Consumer`, `Function`, `Supplier`, etc., **don’t declare checked exceptions** in their method signatures.

Here are a few common approaches to handle exceptions inside lambda expressions in Java:

---

### ✅ 1. **Using Try-Catch Inside the Lambda**

This is the simplest way for **handling exceptions directly** inside the lambda.

```java
List<String> list = Arrays.asList("100", "200", "abc", "300");

list.forEach(item -> {
    try {
        int num = Integer.parseInt(item);
        System.out.println(num);
    } catch (NumberFormatException e) {
        System.err.println("Invalid number: " + item);
    }
});
```

---

### ✅ 2. **Wrapping Lambda with a Custom Functional Interface**

Useful when you want to **reuse** exception handling logic and especially for **checked exceptions**.

```java
@FunctionalInterface
interface CheckedFunction<T, R> {
    R apply(T t) throws Exception;
}

public static <T, R> Function<T, R> wrap(CheckedFunction<T, R> func) {
    return t -> {
        try {
            return func.apply(t);
        } catch (Exception e) {
            throw new RuntimeException(e);  // Or handle differently
        }
    };
}
```

**Usage:**

```java
List<String> list = Arrays.asList("100", "200", "abc");

list.stream()
    .map(wrap(s -> Integer.parseInt(s))) // wrap handles checked exceptions
    .forEach(System.out::println);
```

---

### ✅ 3. **Using Libraries**

There are libraries like **Vavr**, **Apache Commons**, or **FunctionalJava** that offer constructs like `Try`, which make exception handling in lambdas more elegant.

**Example with Vavr:**

```java
import io.vavr.control.Try;

List<String> list = Arrays.asList("100", "abc", "300");

list.stream()
    .map(s -> Try.of(() -> Integer.parseInt(s)).getOrElse(-1))
    .forEach(System.out::println);
```

---

Would you like a reusable utility method or interface to wrap lambdas that throw checked exceptions?
