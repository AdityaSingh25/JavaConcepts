package Multithreading;

import java.util.LinkedList;
import java.util.Queue;

public class QueueComparision {
    public static void main(String[] agrs) throws InterruptedException {
        System.out.println("--- LinkedList Example (Not Thread-Safe) ---");

        Queue<Integer> q = new LinkedList<>();

        Thread producer = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                q.offer(i);
            }
            System.out.println("Producer produced 1000 items in the queue");
        });

        Thread consumer = new Thread(() -> {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            int count = 0;
            while (count < 1000) {
                Integer item = q.poll();
                if (item != null) {
                    count++;
                }
            }
            System.out.println("Consumer processed " + count + " items from LinkedList");
        });

        // Start both threads
        producer.start();
        consumer.start();

        // Wait for completion
        producer.join();
        consumer.join();

        System.out.println("LinkedList size after operations (should be 0): " + q.size());
        System.out.println("Note: LinkedList might have unexpected behavior or exceptions in concurrent scenarios ⚠️");

    }
}
