package src.theory.thread.syncronisationkeyWord;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestCountThread {


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Counter counter = new Counter();
        IntStream.range(0, 1000).forEach(i -> {
            executorService.execute(counter::increment);
        });
        executorService.awaitTermination(1000, TimeUnit.MILLISECONDS);


        System.out.println(counter.getCount());
    }

}
