package Multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Task implements Runnable{

    private final int taskId;

    public Task(int id){
        this.taskId = id;
    }

    @Override
    public void run() {
        System.out.println("Thread " + Thread.currentThread().getId() + " is processing task " + taskId);
        try {
                        // Simulating different thread states
                        Thread.sleep(2000); // Simulates RUNNABLE -> TIMED_WAITING (Sleep)

                      synchronized (this) {
                                System.out.println(Thread.currentThread().getName() + " - WAITING on Task " + taskId);
                               // The thread is now RUNNING and enters a synchronized block.
                                this.wait(2000); // Simulates WAITING state for 1 second
                                // The thread leaves the RUNNING state and enters the WAITING state.
                           }
                                    // After wait() (either by timeout or notify), the thread becomes RUNNABLE again.
                       // When the scheduler picks it, it re-enters the RUNNING state.
                       System.out.println(Thread.currentThread().getName() + " - Task " + taskId + " COMPLETED");
                   } catch (InterruptedException e) {
                       Thread.currentThread().interrupt();
                       // If interrupted while RUNNING, it might transition to TERMINATED or handle the interrupt and continue.
                       // If interrupted while in TIMED_WAITING or WAITING, it will throw InterruptedException and become RUNNABLE.
                    }
               // After the try-catch block, if the pool is still active, the thread will likely go back to the RUNNABLE state,
               // waiting for a new task. If the pool is shutting down, it will eventually move to TERMINATED.
           }
}


public class ThreadPoolLifeCycleDemo {
    private static final int NUM_CORES = Runtime.getRuntime().availableProcessors(); // Get CPU core count
    public static void main(String[] args){
        System.out.println("number of cores on my mac: "+NUM_CORES);
        // Step1 : create a pool of threads. which will manage the threads for us.
        ExecutorService execService = Executors.newFixedThreadPool(3);

        for(int i = 0; i<5;i++){
            execService.execute(new Task(i));
        }
    }
}
