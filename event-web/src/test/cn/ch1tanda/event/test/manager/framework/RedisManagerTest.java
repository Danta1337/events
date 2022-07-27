package cn.ch1tanda.event.test.manager.framework;

import cn.ch1tanda.event.test.BaseTest;
import cn.ch1tanda.event.manager.framework.RedisManager;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

public class RedisManagerTest extends BaseTest {

    @Resource
    private RedisManager redisManager;

    @Test
    public void setEXTest () {
        redisManager.set("test", "test", 100000L);
        String test = redisManager.get("dadas@dasd");
        System.out.println(test);
    }
}
