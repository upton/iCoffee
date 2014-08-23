package name.upton.zest.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureTest {

    /**
     * @param args
     * @throws ExecutionException 
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int count = 10;
        ExecutorService executor = Executors.newFixedThreadPool(count);
        Player p = new Player("upton");
        long start = System.currentTimeMillis();
        Future<String> future = executor.submit(p);
        
        System.out.println("haha");
        Thread.sleep(5000L);
        System.out.println("oooooops");
        
        System.out.println(future.get());
        System.out.println(System.currentTimeMillis() - start);
        
        executor.shutdown();
    }
}

class Player implements Callable<String>{
    
    private String name;
    
    public Player(String name){
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(1000L);
        return "hello " + name;
    }
    
}