package name.upton.zest;

import java.util.Timer;
import java.util.TimerTask;

public class TestTimer {

    /**
     * @param args
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws InterruptedException {
        Timer timer = new Timer("Timer-EnableIndexCache", true);
        
        timer.schedule(new TimerTask() {
            
            @Override
            public void run() {
                System.out.println("1");
            }
        }, 500L);

        Thread.sleep(5000L);
    }

}
