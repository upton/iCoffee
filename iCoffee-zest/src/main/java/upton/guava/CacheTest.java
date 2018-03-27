package upton.guava;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class CacheTest {

    private static Cache<String, String> ch = CacheBuilder.newBuilder().maximumSize(100).build();
    
    public static void main(String[] args) {
        final CyclicBarrier cc = new CyclicBarrier(500);
        
        for(int i = 0; i < 500; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(new Random().nextInt(100));
                        cc.await();
                        System.out.println(System.currentTimeMillis() + getCache("k1"));
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    private static String getCache(final String key) throws ExecutionException{
        return ch.get(key, new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(new Random().nextInt(1000));
                System.out.println("load key:" + System.currentTimeMillis());
                return key;
            }
        });
    }
}
