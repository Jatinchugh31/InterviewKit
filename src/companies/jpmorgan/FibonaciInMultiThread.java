package src.companies.jpmorgan;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.*;

public class FibonaciInMultiThread {

    static final ExecutorService executorService = Executors.newFixedThreadPool(50);
    static final Map<Integer, CompletableFuture<BigInteger>> memo = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int n = 100;
        List<CompletableFuture<BigInteger>> futures = new ArrayList<>();

        // Create a list of futures for Fibonacci sequence calculations
        for (int i = 1; i <= n; i++) {
            futures.add(fibonacciAsync(i));
        }

        // Wait for each result and print
        for (CompletableFuture<BigInteger> future : futures) {
            System.out.println(future.get());
        }

        // Shutdown the executor service
        executorService.shutdown();
    }

    private static CompletableFuture<BigInteger> fibonacciAsync(int n) {
        // Base case for Fibonacci sequence: 0 or 1
        if (n <= 1) {
            return CompletableFuture.completedFuture(BigInteger.valueOf(n));
        }

        // Check memoization map to see if the value is already computed
        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        // Create two separate CompletableFutures for n-1 and n-2 to calculate concurrently
        CompletableFuture<BigInteger> future1 = CompletableFuture.supplyAsync(() -> fibonacciAsync(n - 1).join(), executorService);
        // Combine both futures using thenCombine and return the result
        CompletableFuture<BigInteger> resultFuture = future1.thenCompose(val1 ->
                fibonacciAsync(n - 2).thenApply(val1::add)
        );

        // Store the future in memo to avoid recomputation
        memo.put(n, resultFuture);
        return resultFuture;
    }
}


//  we have a workFlow and we need to do some optation based on condition , which desing pattern ,

// my solution was factory + strategy
// interviewer want factor + chainOfResponsiblity .

// i reply chain of responsiblity if we want to do one action another .    or add not action in between
// not able to answer as per interview because problem is not cleared


//data enycryption