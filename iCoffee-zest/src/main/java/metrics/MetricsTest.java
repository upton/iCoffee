package metrics;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

public class MetricsTest {
    private static final MetricRegistry metrics = new MetricRegistry();
    private static final Random rd = new Random();
    private static final int TIME = 10;
    private static final boolean OPEN = false;

    public static void main(String[] args) {
        ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics).convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS).build();

        reporter.start(60, TimeUnit.SECONDS);

        for (int m = 0; m < 10; m++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < Integer.MAX_VALUE; i++) {
                        biz1();
                    }
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < Integer.MAX_VALUE; i++) {
                        biz2();
                    }
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < Integer.MAX_VALUE; i++) {
                        biz3();
                    }
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < Integer.MAX_VALUE; i++) {
                        biz4();
                    }
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < Integer.MAX_VALUE; i++) {
                        biz5();
                    }
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < Integer.MAX_VALUE; i++) {
                        biz6();
                    }
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < Integer.MAX_VALUE; i++) {
                        biz7();
                    }
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < Integer.MAX_VALUE; i++) {
                        biz8();
                    }
                }
            }).start();
        }
    }

    private static final Timer biz1 = metrics.timer("test.biz1");

    public static void biz1() {
        Timer.Context context = null;
        if(OPEN){
            context = biz1.time();
        }
        
        try {
            try {
                TimeUnit.MILLISECONDS.sleep(rd.nextInt(TIME));
            } catch (Exception e) {
            }
        } finally {
            if(OPEN){
                context.stop();
            }
        }
    }
    private static final Timer biz2 = metrics.timer("test.biz2");
    
    public static void biz2() {
        Timer.Context context = null;
        if(OPEN){
            context = biz2.time();
        }
        
        try {
            try {
                TimeUnit.MILLISECONDS.sleep(rd.nextInt(TIME));
            } catch (Exception e) {
            }
        } finally {
            if(OPEN){
                context.stop();
            }
        }
    }
    private static final Timer biz3 = metrics.timer("test.biz3");
    
    public static void biz3() {
        Timer.Context context = null;
        if(OPEN){
            context = biz3.time();
        }
        try {
            try {
                TimeUnit.MILLISECONDS.sleep(rd.nextInt(TIME));
            } catch (Exception e) {
            }
        } finally {
            if(OPEN){
                context.stop();
            }
        }
    }
    private static final Timer biz4 = metrics.timer("test.biz4");
    
    public static void biz4() {
        Timer.Context context = null;
        if(OPEN){
            context = biz4.time();
        }
        try {
            try {
                TimeUnit.MILLISECONDS.sleep(rd.nextInt(TIME));
            } catch (Exception e) {
            }
        } finally {
            if(OPEN){
                context.stop();
            }
        }
    }
    private static final Timer biz5 = metrics.timer("test.biz5");
    
    public static void biz5() {
        Timer.Context context = null;
        if(OPEN){
            context = biz5.time();
        }
        try {
            try {
                TimeUnit.MILLISECONDS.sleep(rd.nextInt(TIME));
            } catch (Exception e) {
            }
        } finally {
            if(OPEN){
                context.stop();
            }
        }
    }
    private static final Timer biz6 = metrics.timer("test.biz6");
    
    public static void biz6() {
        Timer.Context context = null;
        if(OPEN){
            context = biz6.time();
        }
        try {
            try {
                TimeUnit.MILLISECONDS.sleep(rd.nextInt(TIME));
            } catch (Exception e) {
            }
        } finally {
            if(OPEN){
                context.stop();
            }
        }
    }
    private static final Timer biz7 = metrics.timer("test.biz7");
    
    public static void biz7() {
        Timer.Context context = null;
        if(OPEN){
            context = biz7.time();
        }
        try {
            try {
                TimeUnit.MILLISECONDS.sleep(rd.nextInt(TIME));
            } catch (Exception e) {
            }
        } finally {
            if(OPEN){
                context.stop();
            }
        }
    }
    
    private static final Timer biz8 = metrics.timer("test.biz8");
    
    public static void biz8() {
        Timer.Context context = null;
        if(OPEN){
            context = biz8.time();
        }
        try {
            try {
                TimeUnit.MILLISECONDS.sleep(rd.nextInt(TIME));
            } catch (Exception e) {
            }
        } finally {
            if(OPEN){
                context.stop();
            }
        }
    }

}
