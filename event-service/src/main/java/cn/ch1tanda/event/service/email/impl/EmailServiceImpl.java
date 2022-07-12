package cn.ch1tanda.event.service.email.impl;

import cn.ch1tanda.event.service.email.EmailService;
import cn.ch1tanda.event.service.email.constant.EmailConfigConstants;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Slf4j
@Component
public class EmailServiceImpl implements EmailService {

    @Resource
    private JavaMailSender mailSender;

    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public boolean sendText(String to, String subject, String text) {
        log.info("Sending text email, to:{}, subject:{}, content:{}", to, subject, text);
        threadPoolTaskExecutor.execute(() -> {
            SimpleMailMessage smm = new SimpleMailMessage();
            smm.setFrom(EmailConfigConstants.SEND_FROM);
            smm.setTo(to);
            smm.setSubject(subject);
            smm.setText(text);
            mailSender.send(smm);
        });
        return true;
    }

    @Override
    public boolean sendHtml(String to, String subject, String content) {
        log.info("Sending HTML email, to:{}, subject:{}, content:{}", to, subject, content);
        threadPoolTaskExecutor.execute(() -> {
            try {
                MimeMessage mimeMessage = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
                helper.setFrom(EmailConfigConstants.SEND_FROM);
                helper.setTo(to);
                helper.setSubject(subject);
                helper.setText(content, true);
                mailSender.send(mimeMessage);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        });
        return true;
    }
}
