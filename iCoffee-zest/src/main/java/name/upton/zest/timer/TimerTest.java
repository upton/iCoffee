package name.upton.zest.timer;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TimerTest {

    public static void main(String[] args) {
        ScheduledThreadPoolExecutor st = new ScheduledThreadPoolExecutor(5);
        
        st.scheduleAtFixedRate(new Runnable() {
            private int i = 0;
            @Override
            public void run() {
                System.out.println(i++ + "------------st:" + System.currentTimeMillis());
            }
        }, 1000, 2000, TimeUnit.MILLISECONDS);
        
        Timer tt = new Timer();
        
        tt.scheduleAtFixedRate(new TimerTask() {
            private int i = 0;
            @Override
            public void run() {
                System.out.println(i++ + "------tt:" + System.currentTimeMillis());
            }
        }, 1000, 2000);
    }

}
