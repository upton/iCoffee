package upton.timer;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

    public static void main(String[] args) throws InterruptedException {
        Timer t = new Timer("monitor-sdk-data-process", true);

        t.scheduleAtFixedRate(new TimerTask() {
            int i = 0;

            @Override
            public void run() {
                System.out.println(System.currentTimeMillis() / 1000L + " : " +i);
                i = i + 2;
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, 0, 2000L);

        t.scheduleAtFixedRate(new TimerTask() {
            int i = 1;

            @Override
            public void run() {
                System.out.println(System.currentTimeMillis() / 1000L + " ------------ " +i);
                i = i + 2;
            }

        }, 1000L, 2000L);
        
        Thread.sleep(Long.MAX_VALUE);
    }

}
