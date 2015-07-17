package upton;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestCondition {
    private static final Lock lock = new ReentrantLock();
    private static final Condition cond = lock.newCondition();
    
    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() {
                lock.lock();
                System.out.println(0);
                try {
                    boolean r = cond.await(5, TimeUnit.SECONDS);
                    if(r){
                        System.out.println(111);
                    } else {
                        System.out.println(222);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally{
                    lock.unlock();
                }
                
                
            }
        }).start();
        
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                lock.lock();
                System.out.println(333);
                try {
                    Thread.sleep(6000L);
                    cond.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }).start();
        
        
    }

}
