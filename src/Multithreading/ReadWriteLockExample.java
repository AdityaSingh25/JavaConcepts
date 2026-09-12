package Multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private int commonVariable = 0;

    public static void main(String[] args) {
        ReadWriteLockExample rwObject = new ReadWriteLockExample();

        ExecutorService executor = Executors.newFixedThreadPool(4);

        /*
         - Schedule tasks to simulate the following sequence:
         - 1. Start with three reader tasks concurrently.
         - 2. Then, a writer task updates the log.
         - 3. Next, two readers read the updated value.
         - 4. Then, a second writer task updates the log.
         - 5. Finally, one more reader reads the new value.
        */


        // 3 reads
        executor.submit(() -> rwObject.readFunction("Reader-1"));
        executor.submit(() -> rwObject.readFunction("Reader-2"));
        executor.submit(() -> rwObject.readFunction("Reader-3"));

        // one write
        executor.submit(() -> rwObject.writeFunction("Writer-1", 9));

        // Submit two additional reader tasks.
        executor.submit(() -> rwObject.readFunction("Reader-4"));
        executor.submit(() -> rwObject.readFunction("Reader-5"));

        // Submit a second writer task.
        executor.submit(() -> rwObject.writeFunction("Writer-2", 99));

        // Submit a final reader task.
        executor.submit(() -> rwObject.readFunction("Reader-6"));


        // Shut down the executor.
        executor.shutdown();
        try {
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                System.out.println("Timeout waiting for tasks to finish.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Simulate processing work using a dummy computation loop.
    private void simulateWork() {
        double sum = 0;
        for (double i = 0; i < 500000.00; i++) {
            sum += i;
        }

        // computed sum will be discarded, its purpose is solely to consume CPU time.
    }

    public void readFunction(String taskName) {
        // it will read the common value
        rwLock.readLock().lock();
        try {
            System.out.println(taskName + " (read): Acquired read lock. Reading logValue: " + commonVariable);
            simulateWork();
            System.out.println(taskName + " (read): Finished reading.");
        } finally {
            System.out.println(taskName + " (read): Released read lock.");
            rwLock.readLock().unlock();
        }
    }

    public void writeFunction(String taskName, int newValue) {
        rwLock.writeLock().lock();
        try {
            commonVariable = newValue;
            System.out.println(taskName + " (write): Acquired write lock.");
            simulateWork();
            System.out.println(taskName + " (write): Updated logValue to " + commonVariable);
        } finally {
            System.out.println(taskName + " (write): Released write lock.");
            rwLock.writeLock().unlock();
        }
    }
}
