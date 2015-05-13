package name.upton.zest.blockingqueue;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {
    private static final ArrayBlockingQueue<Integer> chatMsgQueue = new ArrayBlockingQueue<Integer>(100 * 1024);
    
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Thread> ts = new ArrayList<Thread>(15);
        
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Runnable() {

                @Override
                public void run() {
                    int i = 0;
                    while (true) {
                        try {
                            chatMsgQueue.put(i);
                            i++;
                            if(i == 10000000){
                                break;
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            
            t.start();
            ts.add(t);
        }

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    int i = 0;
                    while (true) {
                        try {
                            chatMsgQueue.take();
                            i++;
                            if(i == 10000000){
                                break;
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            
            t.start();
            ts.add(t);
        }
        
        for(Thread t : ts){
            t.join();
        }
        
        System.out.println(chatMsgQueue.size());
        System.out.println("10000000 * 10 done with cost : " + (System.currentTimeMillis() - start));
        
    }
}
