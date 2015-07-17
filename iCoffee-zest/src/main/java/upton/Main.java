package upton;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {
        List<String> masters = new ArrayList<String>();
        masters.add("master1");
        masters.add("master2");
        masters.add("master3");

        Set<String> sentinels = new HashSet<String>();
        sentinels.add("127.0.0.1:26379");
        sentinels.add("127.0.0.1:26479");
        sentinels.add("127.0.0.1:26579");

        final RedisClient redis = new RedisClient(masters, sentinels);
        List<Thread> threads = new ArrayList<Thread>();

        long start = System.currentTimeMillis();
        
        final Random rd = new Random();
        
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Runnable() {
                public void run() {
                    Integer count = 0;
                    Integer success = 0;
                    Integer error = 0;
                    
                    String c = String.valueOf(rd.nextDouble());
                    while (count++ < 30000000) {
                        try {
                            String k = c + String.valueOf(count % 10000);
                            String v = String.valueOf(System.currentTimeMillis());
                            redis.set(k, v);
                            if (!v.equals(redis.get(k))) {
                                logger.warn("not equals");
                            }

                            success++;
                        } catch (Exception ex) {
                            error++;
                        }

                        try {
                            Thread.sleep(2L);
                        } catch (InterruptedException e) {
                            logger.error("error", e);
                        }

                        if (count % 100000 == 0) {
                            logger.info("thread-" + c + " count={}, success={}, error={}", new Object[] { count, success, error });
                        }
                    }

                    logger.info("Thread-" + c + " all success: " + success);
                }
            });
            threads.add(t);
            t.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("All thread done...");
        System.out.println("cost:" + (System.currentTimeMillis() - start));
    }
}
