package Multithreading;

class SharedResource {
    // Demonstration of sleep with synchronization(sync means only one object can access this obj of this class at a time!!!)
    public synchronized void sleepExample() {
        System.out.println(Thread.currentThread().getName() + ": Entering sleepExample");
        try {
            System.out.println(Thread.currentThread().getName() + ": Going to sleep...");
            // Thread.sleep pauses the thread, but the thread still holds the lock
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + ": Woke up!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class SleepExample {
    public static void main(String[] args) {
        final SharedResource shared = new SharedResource();

        // Two threads calling the same sleepExample method
        Thread sleeper1 = new Thread(shared::sleepExample, "Sleeper-1");
        Thread sleeper2 = new Thread(shared::sleepExample, "Sleeper-2");

        // Start both threads almost at the same time.
        sleeper1.start();
        sleeper2.start();
    }
}