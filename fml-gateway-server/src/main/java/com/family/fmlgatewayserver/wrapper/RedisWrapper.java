package com.family.fmlgatewayserver.wrapper;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisWrapper {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    public void addCache(String key, String value) {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set(key, value);
    }
    public void addCache(String key, String value, Long expire) {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set(key, value, expire, TimeUnit.SECONDS);
    }
    public void gddCache(String key, Integer value, Long expire) {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set(key, String.valueOf(value), expire, TimeUnit.SECONDS);
    }
    public String getCache(String key) {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        return ops.get(key);
    }

    public Long getExpire(String key) {
        return stringRedisTemplate.getExpire(key);
    }

    public Boolean hasCache(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    public void delCache(String key) {
        stringRedisTemplate.delete(key);
    }
    public Long increment(String key, Long expire) {
        long count = stringRedisTemplate.opsForValue().increment(key, 1);
        if (count == 1) {
            stringRedisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return count;
    }

    public Long decrement(String key) {
        return stringRedisTemplate.opsForValue().increment(key, -1);
    }

    public Long increment(String key) {
        return stringRedisTemplate.opsForValue().increment(key, 1);
    }
    public void addCache(String key, Map value) {
        HashOperations<String, String, String> opsForHash = stringRedisTemplate.opsForHash();
        opsForHash.putAll(key, value);
    }

    public Map getMap(String mapKey) {
        HashOperations<String, String, String> opsForHash = stringRedisTemplate.opsForHash();
        return opsForHash.entries(mapKey);
    }

    public void putMapValue(String mapKey, String key, String value) {
        HashOperations<String, String, String> opsForHash = stringRedisTemplate.opsForHash();
        opsForHash.put(mapKey, key, value);
    }

    public Set<String> getMapKeys(String mapKey) {
        HashOperations<String, String, String> opsForHash = stringRedisTemplate.opsForHash();
        return opsForHash.keys(mapKey);
    }
    public List<String> getMapValues(String mapKey) {
        HashOperations<String, String, String> opsForHash = stringRedisTemplate.opsForHash();
        return opsForHash.values(mapKey);
    }

    public Object getMapValueByKey(String mapKey, String valueKey) {
        HashOperations<String, String, String> opsForHash = stringRedisTemplate.opsForHash();
        return opsForHash.get(mapKey, valueKey);
    }

    public void delMapValueByKey(String mapKey, String valueKey) {
        HashOperations<String, String, String> opsForHash = stringRedisTemplate.opsForHash();
        opsForHash.delete(mapKey, valueKey);
    }

    public boolean setNx(String key, Long expire) {
        boolean nx = stringRedisTemplate.getConnectionFactory().getConnection().setNX(key.getBytes(), key.getBytes());
        if (nx) {
            stringRedisTemplate.getConnectionFactory().getConnection().expire(key.getBytes(), expire);
        }
        return nx;
    }




}
