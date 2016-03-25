package upton.queue;

import java.util.concurrent.ArrayBlockingQueue;

public class QueueTest {

    public static void main(String[] args) {
        final ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    queue.offer(i);
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Integer i = queue.peek();
                    if (i != null) {
                        System.out.println(i);
                        queue.remove();
                    } else {
                        System.out.println(i);
                        try {
                            Thread.sleep(1000L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

    }

}
