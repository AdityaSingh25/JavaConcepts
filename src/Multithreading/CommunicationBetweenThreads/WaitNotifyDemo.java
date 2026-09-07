package Multithreading.CommunicationBetweenThreads;

public class WaitNotifyDemo {

    private final Object lock = new Object();

    private boolean conditionMet = false;

    public static void main(String[] args) {
        WaitNotifyDemo demo = new WaitNotifyDemo();

        Thread t1 = new Thread(demo::doWait, "Waiter-1");
        Thread t2 = new Thread(demo::doWait, "Waiter-2");
        Thread t3 = new Thread(demo::doWait, "Waiter-3");

        t1.start();
        t2.start();
        t3.start();

        // Sleep to ensure all waiting threads have started and are waiting.
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Thread notifier = new Thread(demo::doNotify, "Notifier");
        notifier.start();
    }

    public void doWait() {
        synchronized (lock) {
            while (!conditionMet) {
                try {
                    System.out.println("Thread : " + Thread.currentThread().getId() + " is now waiting....");
                    lock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println(Thread.currentThread().getName() + " was interrupted " + " with message " + e);
                }
            }
            System.out.println(Thread.currentThread().getName() + " resumed execution.");
        }
    }

    public void doNotify() {
        synchronized (lock) {
            conditionMet = true;
            System.out.println(Thread.currentThread().getName() + " called notify().");
            lock.notify();  // Wakes up one waiting thread (if any)
        }
    }

    public void doNotifyAll() {
        synchronized (lock) {
            conditionMet = true;
            System.out.println(Thread.currentThread().getName() + " called notifyAll().");
            lock.notifyAll();  // Wakes up all waiting threads
        }
    }
}
