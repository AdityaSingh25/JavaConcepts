package Multithreading;

import java.util.concurrent.Semaphore;

public class MultipleSemaphore {
    private static final Semaphore mutex = new Semaphore(3); // Binary semaphore with 1 permit

    private static void criticalSection(String threadName) {
        try {
            System.out.println(threadName + " is attempting to acquire the lock.");
            mutex.acquire(); // Acquire the semaphore
            System.out.println(threadName + " acquired the lock.");
            Thread.sleep(15000); // Simulate work in the critical section
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            mutex.release(); // Release the semaphore
            System.out.println(threadName + " released the lock.");
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> criticalSection("thread-1"));
        Thread t2 = new Thread(() -> criticalSection("thread-2"));
        Thread t3 = new Thread(() -> criticalSection("thread-3"));
        Thread t4 = new Thread(() -> criticalSection("thread-4"));
        Thread t5 = new Thread(() -> criticalSection("thread-5"));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
