package cn.ch1tanda.event.test.service.common;

import cn.ch1tanda.event.test.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import javax.annotation.Resource;

public class EmailServiceTest extends BaseTest {

    @Resource
    private MailSender mailSender;

    @Test
    public void sendMailTest() {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setFrom("ch1tanda@outlook.com");
        smm.setTo("997847038@qq.com");
        smm.setSubject("123");
        smm.setText("test email");
        mailSender.send(smm);
    }
}
