package cn.ch1tanda.event.manager.framework;

public interface RedisManager {

    void set(String key, String value);

    void set(String key, String value, Long expireMillis);

    Boolean setIfAbsent(String key, String value);

    Boolean setIfAbsent (String key, String value, Long expireMillis);

    String get(String key);
}
