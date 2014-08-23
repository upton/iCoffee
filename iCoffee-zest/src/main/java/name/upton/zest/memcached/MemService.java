package name.upton.zest.memcached;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import net.spy.memcached.MemcachedClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemService {
    @Autowired
    private MemcachedClient memcachedClient;
    
    public void setValue(String key, String value) throws InterruptedException, ExecutionException{
        Future<Boolean> ff = memcachedClient.set(key, 1000, value);
        ff.get();
    }
    
    public String getValue(String key){
        return (String)memcachedClient.get(key);
    }
    
    
}
