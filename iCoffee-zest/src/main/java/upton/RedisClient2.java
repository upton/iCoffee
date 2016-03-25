package upton;

import java.util.ArrayList;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class RedisClient2 {
    private ShardedJedisPool pool = null;

    public RedisClient2() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(20);
        config.setMinIdle(10);
        config.setMaxWaitMillis(1000L);
        config.setTestOnBorrow(false);

        pool = new ShardedJedisPool(config, new ArrayList<JedisShardInfo>(){
            private static final long serialVersionUID = 1L;

            {
                add(new JedisShardInfo("192.168.8.236", 6379));
            }
        });
    }
    
    public ShardedJedis getJedis(){
        return pool.getResource();
    }
    
    public String set(String key, String value) {
        ShardedJedis jedis = pool.getResource();

        try {
            return jedis.set(key, value);
        } finally {
            jedis.close();
        }
    }

    public String get(String key) {
        ShardedJedis jedis = pool.getResource();

        try {
            return jedis.get(key);
        } finally {
            jedis.close();
        }
    }
}
