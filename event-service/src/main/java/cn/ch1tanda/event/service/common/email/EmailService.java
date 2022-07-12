package cn.ch1tanda.event.service.common.email;

/**
 * 电子邮件服务
 */
public interface EmailService {

    /**
     * 发送文本邮件
     * @param to 收件人邮箱
     * @param subject 邮件主题
     * @param text 邮件内容
     * @return 处理结果
     */
    boolean sendText(String to, String subject, String text);
}
