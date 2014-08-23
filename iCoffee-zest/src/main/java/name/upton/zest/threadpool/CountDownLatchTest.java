package name.upton.zest.threadpool;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        testPlayer();
    }

    public static void testCountDuwnLatch() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                latch.countDown();
                System.out.println("done1");
            }
        }).start();

        Thread.sleep(4000L);
        System.out.println("done2");

        latch.await();

        System.out.println("all done");
    }

    public static void testPlayer() throws InterruptedException {
        int count = 10;
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(count);

        System.out.println("Ready?");
        for (int i = 0; i < count; i++) {
            new Thread(new Player("" + (i + 1), start, end)).start();
        }

        Thread.sleep(10L);
        System.out.println("Go!");
        start.countDown();

        end.await();
        System.out.println("All player done.");
    }

    static class Player implements Runnable {
        private String id;
        private CountDownLatch start;
        private CountDownLatch end;

        public Player(String id, CountDownLatch start, CountDownLatch end) {
            this.id = id;
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            try {
                System.out.println("player " + id + " ready!");
                start.await();

                int tt = new Random().nextInt(5000);
                Thread.sleep(tt);
                System.out.println("player " + id + ", tt=" + tt);
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                end.countDown();
            }
        }
    }
}
