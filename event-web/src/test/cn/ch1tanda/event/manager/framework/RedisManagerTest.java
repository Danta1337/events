package cn.ch1tanda.event.manager.framework;

import cn.ch1tanda.event.EventApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest(classes = EventApplication.class)
public class RedisManagerTest {

    @Resource
    private RedisManager redisManager;

    @Test
    public void setEXTest () {
        redisManager.set("test", "test", 100000L);
        String test = redisManager.get("test");
        System.out.println(test);
    }
}
