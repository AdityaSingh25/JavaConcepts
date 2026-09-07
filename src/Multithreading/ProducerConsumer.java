package Multithreading;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer {

    private final Queue<Integer> buffer = new LinkedList<>();
    private final int CAPACITY = 5;

    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (this) {
                // wait if the buffer is full.
                while (buffer.size() == CAPACITY) {
                    System.out.println("Producer has to wait as the buffer is full");
                    wait();
                }

                // once there is a space produce an item
                System.out.println("Producer produced : " + value);
                buffer.offer(value++);

                // Notify all waiting threads (consumers) that a new item is available.
                notifyAll();
            }
            // Sleep for a short time to simulate production time.
            Thread.sleep(1000);
        }
    }

    public void consume() throws InterruptedException{
        while(true){
            synchronized(this){
                // wait till the buffer is empty

                while(buffer.isEmpty()){
                    System.out.println("Consumer is waiting as the buffer is empty");
                    wait();
                }

                // once there is an item in the buffer consume it
                int value = buffer.poll();
                System.out.println("Consumer consumed: " + value);

                // Notify all waiting threads (producers) that space is available.
                notifyAll();
            }

            Thread.sleep(1500);
        }
    }

    public static void main(String[] args){
        ProducerConsumer pc = new ProducerConsumer();

        Thread producer = new Thread(()->{
            try {
                pc.produce();
            }catch(InterruptedException e){
                System.out.println("Caught in the exception : "+ e);
            }
        }, "ProducerThread");

        Thread consumer = new Thread(()->{
            try {
                pc.consume();
            }catch(InterruptedException e){
                System.out.println("Caught in the exception : "+ e);
            }
        }, "ConsumerThread");


        producer.start();
        consumer.start();

    }
}
