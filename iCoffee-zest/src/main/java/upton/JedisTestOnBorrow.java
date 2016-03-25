package upton;

import java.util.concurrent.TimeUnit;

public class JedisTestOnBorrow {

    public static void main(String[] args) throws InterruptedException {
        final RedisClient2 redis = new RedisClient2();
        final int num = 300;
        final int threads = 20;
        for(int i = 0; i < threads; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long start = System.currentTimeMillis();

                    for(int j = 0; j < num; j++){
                        String value = redis.get("k" + j);
                        System.out.println(Thread.currentThread().getName() + ", k" + j + " = " + value);
                    }
                    
                    long end = System.currentTimeMillis();
                    System.out.println("=================" + Thread.currentThread().getName() + " time : " + (end - start));
                }
            }, "t-" + i).start();
        }
        
        
        TimeUnit.DAYS.sleep(1);
    }
}
