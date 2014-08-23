package name.upton.zest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock {

    /**
     * @param args
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws InterruptedException {
        ProcessCommandThread processCommand = new ProcessCommandThread();
        
        processCommand.start();
        
        Thread.sleep(1000L);
        
        processCommand.insertCommand("test");
    }
}

class ProcessCommandThread extends Thread {
    private boolean active = true;
    final Lock cmdLock = new ReentrantLock();
    final Condition hasCommand = cmdLock.newCondition();
    List<String> commInfos = new ArrayList<String>();

    @Override
    public void run() {
        while (active) {
            try {
                // 枷锁，进入等待状态
                cmdLock.lock();
                if (commInfos.isEmpty()) {
                    System.out.println(4);
                    hasCommand.await();
                    System.out.println(1);
                }
                cmdLock.unlock();
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopThread() {
        active = false;
        hasCommand.signal();
    }

    public boolean insertCommand(String commInfo) {
        boolean result;
        cmdLock.lock();
        try {
            System.out.println(2);
            result = commInfos.add(commInfo);
            hasCommand.signal();
            System.out.println(3);
        } finally {
            cmdLock.unlock();
        }
        return result;
    }
}
