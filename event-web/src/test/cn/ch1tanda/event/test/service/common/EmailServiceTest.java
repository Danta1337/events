package cn.ch1tanda.event.test.service.common;

import cn.ch1tanda.event.service.generic.email.EmailService;
import cn.ch1tanda.event.test.BaseTest;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

public class EmailServiceTest extends BaseTest {

    @Resource
    private EmailService emailService;

    @Test
    public void sendMailTest() throws InterruptedException {
        boolean sendResult = emailService.sendText("997847038@qq.com", "123", "test");
        System.out.println(sendResult);
        Thread.sleep(10000L);
    }

    @Test
    public void sendMailHtmlTest() throws InterruptedException {
        boolean sendResult = emailService.sendHtml("997847038@qq.com", "123", "<html><body><h1>test</h1></body></html>");
        System.out.println(sendResult);
        Thread.sleep(10000L);
    }
}
