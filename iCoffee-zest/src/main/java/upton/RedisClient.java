package upton;

import java.util.List;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.ShardedJedis;
import upton.cache.ShardedJedisSentinelPool;

public class RedisClient {
    private ShardedJedisSentinelPool pool = null;

    public RedisClient(List<String> masters, Set<String> sentinels) {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(20);
        config.setMinIdle(10);
        config.setMaxWaitMillis(1000L);

        pool = new ShardedJedisSentinelPool(masters, sentinels, config, 1000);
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
