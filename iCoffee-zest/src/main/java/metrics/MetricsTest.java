package metrics;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.SlidingTimeWindowReservoir;
import com.codahale.metrics.Timer;

public class MetricsTest {

    public static void main(String[] args) {

        reg.register("aaa.biz", timer);
        
        ConsoleReporter reporter = ConsoleReporter.forRegistry(reg).convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS).build();
        
        reporter.start(60, TimeUnit.SECONDS);
        
        for(int m = 0; m < 10; m++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i = 0; i < Integer.MAX_VALUE; i++){
                        biz();
                    }
                }
            }).start();
            
        }
    }

    private static final MetricRegistry reg = new MetricRegistry();
    private static final Random rd = new Random();
    
    private static final Timer timer = new Timer(new SlidingTimeWindowReservoir(60, TimeUnit.SECONDS));

    public static void biz() {
        final Timer.Context context = timer.time();
        try {
            try {
//                if(rd.nextInt(10) % 9 == 0){
//                    TimeUnit.MILLISECONDS.sleep(1);
//                }
                TimeUnit.MILLISECONDS.sleep(2);
            } catch (Exception e) {
            }
        } finally {
            context.stop();
        }
    }

}
