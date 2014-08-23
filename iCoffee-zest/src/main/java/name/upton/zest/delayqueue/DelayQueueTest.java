package name.upton.zest.delayqueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}

class Item implements Delayed{

    @Override
    public int compareTo(Delayed o) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
