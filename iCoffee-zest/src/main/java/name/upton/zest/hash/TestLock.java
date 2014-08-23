package name.upton.zest.hash;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock {
    ReentrantLock lock = new ReentrantLock();
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        ConcurrentHashMap<String, String> mm = new ConcurrentHashMap<String, String>();
        
        String value1 = mm.putIfAbsent("key1", "value1");
        System.out.println(value1);
        value1 = mm.putIfAbsent("key1", "value2");
        System.out.println(value1);
        value1 = mm.putIfAbsent("key1", "value2");
        System.out.println(value1);
        value1 = mm.putIfAbsent("key1", "value2");
        System.out.println(value1);
    }

}
