package Multithreading;

//A barrier ensures that no thread can proceed past a certain point until all threads have reached that point. Here's how to implement it with semaphores:

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadFactory;

public class BarrierWithSemaphore {
    public static void main(String[] args) {
        final int numThreads = 5;

        final SemaphoreBarrier barrier = new SemaphoreBarrier(numThreads);

        ExecutorService executor = Executors.newFixedThreadPool(numThreads, new ThreadFactory() {
            private int counter = 1;

            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r, "Worker-" + counter);
                counter++;
                return t;
            }
        });
        ;
        for (int i = 1; i <= numThreads; i++) {
            executor.submit(() -> {
                try {
                    // Phase 1: Some work before reaching the first barrier
                    System.out.println(Thread.currentThread().getName() + " doing phase 1 work");
                    Thread.sleep(7000); // Simulate work
                    System.out.println(
                            Thread.currentThread().getName() + " arrived at barrier after phase 1");

                    barrier.await(); // Wait until all threads reach here



                    // Phase 2: This phase begins only after every thread has finished phase 1
                    System.out.println(Thread.currentThread().getName() + " starting phase 2");
                    Thread.sleep(7000); // Simulate work
                    System.out.println(Thread.currentThread().getName() + " finished phase 2");

                    barrier.await(); // Synchronize end of phase 2


                    // Phase 3: The final phase starts after all threads have completed phase 2
                    System.out.println(Thread.currentThread().getName() + " starting phase 3");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println(Thread.currentThread().getName() + " was interrupted");
                }
            });
        }
    }


    static class SemaphoreBarrier {
        private final int parties;
        private final Semaphore mutex = new Semaphore(1);
        private final Semaphore barrier = new Semaphore(0);
        private int count;

        public SemaphoreBarrier(int parties) {
            this.parties = parties;// parties is just to maintain the initial count 9to remember)
            this.count = parties;
        }

        public void await() throws InterruptedException {
            mutex.acquire();
            count--;
            if (count == 0) {

                // Last thread arrives: release all waiting threads
                barrier.release(parties - 1); // to release rest of the threads who waits on the barrier.acquire(); due to the 0 semaphore.


                // Reset barrier state for reuse
                count = parties;


                // for the last thread to release the lock.
                mutex.release();
            } else {
                // Release mutex so other threads can update the count
                mutex.release();

                // Wait until the last thread releases this thread
                barrier.acquire();
            }
        }
    }
}
