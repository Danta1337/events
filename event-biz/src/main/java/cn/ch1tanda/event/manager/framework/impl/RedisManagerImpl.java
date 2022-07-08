package cn.ch1tanda.event.manager.framework.impl;

import cn.ch1tanda.event.manager.framework.RedisManager;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Component
public class RedisManagerImpl implements RedisManager {

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void set(String key, String value, Long expireMillis) {
        stringRedisTemplate.opsForValue().set(key, value, Duration.ofMillis(expireMillis));
    }

    public Boolean setIfAbsent(String key, String value) {
        return stringRedisTemplate.opsForValue().setIfAbsent(key, value);
    }

    public Boolean setIfAbsent(String key, String value, Long expireMillis) {
        return stringRedisTemplate.opsForValue().setIfAbsent(key, value, Duration.ofMillis(expireMillis));
    }
    @Override
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public String getAndDelete(String key) {
        return stringRedisTemplate.opsForValue().getAndDelete(key);
    }

    @Override
    public String getAndExpire(String key, Long timeout) {
        return stringRedisTemplate.opsForValue().getAndExpire(key, timeout, TimeUnit.MILLISECONDS);
    }

    @Override
    public String getAndExpire(String key, Long timeout, TimeUnit timeUnit) {
        return stringRedisTemplate.opsForValue().getAndExpire(key, timeout, timeUnit);
    }
}
