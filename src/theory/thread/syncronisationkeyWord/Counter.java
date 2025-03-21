package src.theory.thread.syncronisationkeyWord;

public class Counter {
    private static int staticCount = 0;
    private int count = 0;

    // static level method syncomised  //class level
    public  static  void  incrementStatic(){
        staticCount++;
    }

    public static int getStaticCount() {
        return staticCount;
    }

    // this is instance method syncronised level
    public synchronized void increment() {
        count++;
    }


    public void decrement() {

        //class level block locking
        synchronized (Counter.class) {
            count--;

        }

        //object level block locking
        synchronized (this) {
            count--;

        }
    }

    public int getCount() {
        return count;
    }
}
