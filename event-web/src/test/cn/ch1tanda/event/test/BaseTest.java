package cn.ch1tanda.event.test;

import cn.ch1tanda.event.EventApplication;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 其余测试类可以继承该类，直接获取SpringBoot的启动器配置
 */
@SpringBootTest(classes = EventApplication.class)
public class BaseTest {
}
