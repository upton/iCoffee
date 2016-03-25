package upton.timer;

import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ExcuterTest {

    public static void main(String[] args) throws InterruptedException {
        final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2, new ThreadFactory(){
            AtomicInteger idx = new AtomicInteger(0);
            @Override
            public Thread newThread(Runnable r) {
                // TODO Auto-generated method stub
                return null;
            }
            
        });

        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            int i = 1;

            @Override
            public void run() {
                System.out.println(System.currentTimeMillis() / 1000L + " : " + i);
                i = i + 2;
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, 0, 2, TimeUnit.SECONDS);
        
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            int i = 1;

            @Override
            public void run() {
                System.out.println(System.currentTimeMillis() / 1000L + " ----- " + i);
                i = i + 2;
            }
        }, 0, 2, TimeUnit.SECONDS);

        Runtime.getRuntime().addShutdownHook(new Thread("clean-thread"){
            
            @Override
            public void run() {
                scheduledExecutorService.shutdown();
            }
        });
    }

}
