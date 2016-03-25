package upton.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class TestPutIfAbsent {
    private static ConcurrentHashMap<String, AtomicInteger> concurrents = new ConcurrentHashMap<>();
    private static final int NUM = 1000 * 10000;

    public static void main(String[] args) throws InterruptedException {
        List<Thread> ts = new ArrayList<Thread>();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < NUM; j++) {
                        String key = String.valueOf(j % 10);
                        m2(key);
                    }

                }
            });

            t.start();
            ts.add(t);
        }

        for (Thread t : ts) {
            t.join();
        }

        long s1 = System.currentTimeMillis();

        System.out.println(s1 - start);

        // clear---------------------
        concurrents.clear();
        ts.clear();
        // --------------------------
        
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < NUM; j++) {
                        String key = String.valueOf(j % 10);
                        m1(key);
                    }

                }
            });

            t.start();
            ts.add(t);
        }

        for (Thread t : ts) {
            t.join();
        }

        long s2 = System.currentTimeMillis();

        System.out.println(s2 - s1);
        
        // clear---------------------
        concurrents.clear();
        ts.clear();
        // --------------------------
        
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < NUM; j++) {
                        String key = String.valueOf(j % 10);
                        m2(key);
                    }
                }
            });
            
            t.start();
            ts.add(t);
        }
        
        for (Thread t : ts) {
            t.join();
        }
        
        long s3 = System.currentTimeMillis();
        
        System.out.println(s3 - s2);
        
    }

    private static AtomicInteger m1(String key) {
        AtomicInteger concurrent = concurrents.get(key);
        if (concurrent == null) {
            concurrents.putIfAbsent(key, new AtomicInteger());
            concurrent = concurrents.get(key);
        }

        return concurrent;
    }

    private static AtomicInteger m2(String key) {
        return concurrents.putIfAbsent(key, new AtomicInteger());
    }
}
