package cn.ch1tanda.event.service.common.email.impl;

import cn.ch1tanda.event.service.common.email.EmailService;
import cn.ch1tanda.event.service.common.email.constant.EmailConfigConstants;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class EmailServiceImpl implements EmailService {

    @Resource
    private MailSender mailSender;

    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public boolean sendText(String to, String subject, String text) {
        threadPoolTaskExecutor.execute(() -> {
            SimpleMailMessage smm = new SimpleMailMessage();
            smm.setFrom(EmailConfigConstants.SEND_FROM);
            smm.setTo(to);
            smm.setSubject(subject);
            smm.setText(text);
            mailSender.send(smm);
        });
        return false;
    }
}
