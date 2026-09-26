package Multithreading;

import java.util.concurrent.Semaphore;

public class ReaderWriterLockUsingSemaphore {
    // Count of active readers.
    private int readerCount = 0;

    // Semaphore acting as a mutex for protecting the readerCount variable.
    private final Semaphore mutex = new Semaphore(1);

    // Semaphore that allows writers (or the first reader) to acquire exclusive access.
    private final Semaphore wrt = new Semaphore(1);

    // Called by a reader to acquire the read lock.
    public void lockRead() throws InterruptedException {
        // Acquire the mutex to update the reader count safely.
        mutex.acquire();
        readerCount++;

        // If this is the first reader, acquire the write semaphore to block writers.
        if (readerCount == 1) {
            wrt.acquire();
        }

        // Release the mutex so other readers or writers can update the reader count.
        mutex.release();
    }

    // Called by a reader to release the read lock.
    public void unlockRead() throws InterruptedException {
        // Acquire the mutex to update the reader count safely.
        mutex.acquire();
        readerCount--;

        // If no readers remain, release the write lock, allowing writers to proceed.
        if (readerCount == 0) {
            wrt.release();
        }
        mutex.release();
    }

    // Called by a writer to acquire the write lock.
    public void lockWrite() throws InterruptedException {
        // Writers acquire the write semaphore directly.
        wrt.acquire();
    }

    // Called by a writer to release the write lock.
    public void unlockWrite() {
        wrt.release();
    }

    // --- Sample usage ---
    public static void main(String[] args) {
        ReaderWriterLockUsingSemaphore rwLock = new ReaderWriterLockUsingSemaphore();

        // Sample reader thread
        Runnable readerTask = () -> {
            try {
                rwLock.lockRead();
                System.out.println(Thread.currentThread().getName() + " is reading.");
                // Simulate reading time
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName() + " finished reading.");
                rwLock.unlockRead();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        // Sample writer thread
        Runnable writerTask = () -> {
            try {
                rwLock.lockWrite();
                System.out.println(Thread.currentThread().getName() + " is writing.");
                // Simulate writing time
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName() + " finished writing.");
                rwLock.unlockWrite();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        // Start sample reader and writer threads
        Thread reader1 = new Thread(readerTask, "Reader-1");
        Thread reader2 = new Thread(readerTask, "Reader-2");
        Thread writer1 = new Thread(writerTask, "Writer-1");

        reader1.start();
        reader2.start();
        writer1.start();
    }
}

