package src.theory.thread;

import java.util.concurrent.locks.ReentrantLock;

public class PrintCounting {
    private static final Object lock = new Object();
    static int counter =1;
    static int counter2 =1;

    private  static  final ReentrantLock lock1 = new ReentrantLock();
    public static void main(String[] args) {
        int max=10;
        way1(max);
        //way2(max);
    }

    private static void way2(int max) {

        Runnable runnable = () -> {
            while (true) {
                lock1.lock();
                try {
                    if (counter2 > max) {
                        return; // Exit the thread cleanly
                    }
                    System.out.println(Thread.currentThread().getName() + ": " + counter2);
                    counter2++;
                } finally {
                    lock1.unlock();
                }
            }
        };

        Thread t1 = new Thread(runnable, "Reentrant-Thread-A");
        Thread t2 = new Thread(runnable, "Reentrant-Thread-B");
        Thread t3 = new Thread(runnable, "Reentrant-Thread-C");
        t1.start();
        t2.start();
        t3.start();
    }
  //  modulor logic

    //  1 -1 % 3 =  current =0
    private static void way1(int max) {
        Runnable runnable = () -> {
            String name = Thread.currentThread().getName();
            int threadId = Integer.parseInt(name.split("-")[1]);

            while (true) {
                synchronized (lock) {
                    System.out.println( ((counter - 1) % 3 )+ ""+ (threadId - 1));
                    while (counter <= max && (counter - 1) % 3 != threadId - 1) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    if (counter > max) {
                        break;
                    }
                    System.out.println(name + " prints: " + counter);
                    counter++;
                    lock.notifyAll();
                }
            }

        };
        Thread t1 = new Thread(runnable, "Thread-1");  // 1  4  7  10    1 % 3 = 1
        Thread t2 = new Thread(runnable, "Thread-2");  // 2  5  8
        Thread t3 = new Thread(runnable, "Thread-3");   //  3  6  9

        t1.start();
        t2.start();
        t3.start();
    }


}
