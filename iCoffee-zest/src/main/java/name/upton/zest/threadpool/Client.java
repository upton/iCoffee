package name.upton.zest.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Client {

    /**
     * @param args
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor myThreadPool = new ThreadPoolExecutor(4, 4, 3, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1),
                new MyThreadFactory());

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            while(!myThreadPool.getQueue().isEmpty()){
                Thread.sleep(1L);
            }
            myThreadPool.execute(new MyTask(String.valueOf(i)));
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    static class MyThreadFactory implements ThreadFactory {
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        MyThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix = "MyThreadPool-thread-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }

            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }

            return t;
        }
    }
}
