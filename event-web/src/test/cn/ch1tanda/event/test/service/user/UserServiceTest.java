package cn.ch1tanda.event.test.service.user;

import cn.ch1tanda.event.service.user.UserService;
import cn.ch1tanda.event.test.BaseTest;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

public class UserServiceTest extends BaseTest {

    @Resource
    private UserService userService;

    @Test
    public void sendEmailTest() throws InterruptedException {
        String code = userService.sendEmailVerificationCode("997847038@qq.com");
        System.out.println(code);
        // 线程睡眠20s，等待邮件发送
        Thread.sleep(20000L);
    }

    @Test
    public void verifyEmail() {
        Boolean aBoolean = userService.verifyEmailVerificationCode("997847038@qq.com", "288570");
        System.out.println(aBoolean);
    }
}
