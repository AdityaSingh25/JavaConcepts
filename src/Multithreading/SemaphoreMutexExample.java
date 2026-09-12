package Multithreading;

import java.util.concurrent.Semaphore;

public class SemaphoreMutexExample {
    private static final Semaphore mutex = new Semaphore(1); // Binary semaphore with 1 permit

    private static void criticalSection(String threadName) {
        try {
            System.out.println(threadName + " is attempting to acquire the lock.");
            mutex.acquire(); // Acquire the semaphore
            System.out.println(threadName + " acquired the lock.");
            Thread.sleep(17000); // Simulate work in the critical section
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

        t1.start();
        t2.start();
    }
}
