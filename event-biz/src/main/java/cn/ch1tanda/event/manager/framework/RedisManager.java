package cn.ch1tanda.event.manager.framework;

import java.util.concurrent.TimeUnit;

public interface RedisManager {

    /**
     * 设置缓存
     */
    void set(String key, String value);

    /**
     * 设置缓存，带过期时间
     * @param expireMillis 过期时间，单位：ms
     */
    void set(String key, String value, Long expireMillis);

    /**
     * 如果该key不存在，则设置缓存
     * @return true if the key was set
     * <p>false if the key was not set</p>
     */
    Boolean setIfAbsent(String key, String value);

    /**
     * 如果该key不存在，则设置缓存
     * @param expireMillis 过期时间，单位：ms
     * @return true if the key was set
     * <p>false if the key was not set</p>
     */
    Boolean setIfAbsent (String key, String value, Long expireMillis);

    /**
     * 获取缓存
     * @return if the key exists, return the value
     * <p>else return null</p>
     */
    String get(String key);

    /**
     * 获取缓存并删除该缓存
     * @return if the key exists, return the value
     * <p>else return null</p>
     */
    String getAndDelete(String key);

    /**
     * 获取缓存并重新设置超时时间
     * @param timeout 超时时间，单位：ms
     * @return if the key exists, return the value
     * <p>else return null</p>
     */
    String getAndExpire(String key, Long timeout);

    /**
     * 获取缓存并重新设置超时时间
     * @param timeout 超时时间
     * @param timeUnit 时间单位
     * @return if the key exists, return the value
     * <p>else return null</p>
     */
    String getAndExpire(String key, Long timeout, TimeUnit timeUnit);
}
