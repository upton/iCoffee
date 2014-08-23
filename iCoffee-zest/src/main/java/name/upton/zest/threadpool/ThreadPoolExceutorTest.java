package name.upton.zest.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExceutorTest {
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 8, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(10));

    /**
     * @param args
     */
    public static void main(String[] args) {
        int i = 30;

        while (i-- > 0) {
            while (true) {
                if (executor.getQueue().size() < 10) {
                    executor.submit(new Task());
                    break;
                } else {
                    try {
                        Thread.sleep(10L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(2 * 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
