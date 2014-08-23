package name.upton.zest.memcached;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import net.spy.memcached.MemcachedClient;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MemTest {
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/name/upton/zest/memcached/mem.xml");
        
        MemcachedClient client = ctx.getBean(MemcachedClient.class);
        
        Future<Boolean> ff = client.set("aa", 1000, "AAA");
        ff.get();
        
        System.out.println(client.get("aa"));
    }

}
