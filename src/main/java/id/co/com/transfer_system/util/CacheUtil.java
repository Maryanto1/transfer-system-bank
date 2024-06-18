package id.co.com.transfer_system.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class CacheUtil {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void putCache(String cacheName, Object key, Object value, long timeToLive){
        redisTemplate.opsForValue().set(cacheName + "|" + key, value, timeToLive, TimeUnit.SECONDS);
    }

    public void putCache(String cacheName, Object key, Object value){
        redisTemplate.opsForValue().set(cacheName + "|" + key, value);
    }

    public Object getCache(String cacheName, Object key){
        return redisTemplate.opsForValue().get(cacheName + "|" + key);
    }

    public void removeCache (String cacheName, Object key){
        redisTemplate.opsForValue().getOperations().delete(cacheName + "|" + key);
    }

    public void removeCache (String cacheName){
        Set<String> setKeys = redisTemplate.keys(cacheName+"*");
        redisTemplate.opsForValue().getOperations().delete(setKeys);
    }
}
