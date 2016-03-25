package upton.list;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

public class ListTest {

    public static void main(String[] args) throws InterruptedException {
        // final ArrayList<Integer> list = new ArrayList<>(5 * 1000000);
        final ArrayBlockingQueue<Integer> list = new ArrayBlockingQueue<>(5 * 1000000);
        final ArrayList<Thread> ts = new ArrayList<>();

        for (int count = 0; count < 5; count++) {
            final int idx = count;
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000000; i++) {
                        list.add(i);
                    }
                    
                    System.out.println("Thread " + idx + " is done");
                }
            });
            
            ts.add(t);
            t.start();
        }
        
        for(Thread t : ts){
            t.join();
        }
        
        System.out.println(list.size());
    }

}
