package Multithreading;


class CustomThread implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Thread " + Thread.currentThread().threadId() + " is running: " + i);
            try {
                Thread.sleep(2000); /// pause execution for 500 ms
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
            }
        }
    }
}

public class UsingRunnnable {
    public static void main(String[] args) {

        Runnable cu1 = new CustomThread();

        Thread t1 = new Thread(cu1);
        Thread t2 = new Thread(cu1);
        t1.start();
        t2.start();

        // lambda
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread " + Thread.currentThread().threadId() + " is running: " + i);
                try {
                    Thread.sleep(2000); /// pause execution for 500 ms
                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted");
                }
            }
        });
        t3.start();
    }
}
