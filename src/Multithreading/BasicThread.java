package Multithreading;

public class BasicThread extends Thread {

    @Override
    // In Java, there is a strict rule regarding access modifiers: when you override a parent method, you cannot reduce its visibility. You can only keep it the same or make it more accessible. that's why its public not private
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Thread " + Thread.currentThread().threadId() + " is running: " + i);
            try {
                Thread.sleep(5000); /// pause execution for 500 ms
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
            }
        }
    }

    public static void main(String[] args) {
        BasicThread t1 = new BasicThread(); // instance of thread
        BasicThread t2 = new BasicThread(); // instance of thread

        t1.start();
        t2.start();
    }


}
