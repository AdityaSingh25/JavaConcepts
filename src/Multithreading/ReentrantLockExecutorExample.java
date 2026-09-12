package Multithreading;
import lombok.Getter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
public class ReentrantLockExecutorExample {

    @Getter
    private int counter = 0;

    // create a reentrantlock lock instance
    private final ReentrantLock reLock = new ReentrantLock();

    // method to increase the counter using lock.
    public void increment(){
        reLock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + " acquired the lock.");
            counter++;
            System.out.println(Thread.currentThread().getName() + " incremented counter to: " + counter);
        } finally {
            System.out.println(Thread.currentThread().getName() + " released the lock.");
            reLock.unlock();
        }
    }
    public static void main(String[] args){
        ReentrantLockExecutorExample reObject = new ReentrantLockExecutorExample();

        // Create an ExecutorService with a fixed thread pool of 5 threads.
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(int i = 0 ; i<5;i++){
            executorService.submit(()->reObject.increment());
        }
    }
}
