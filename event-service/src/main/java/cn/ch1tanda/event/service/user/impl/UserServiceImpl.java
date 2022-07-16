package cn.ch1tanda.event.service.user.impl;

import cn.ch1tanda.event.exception.ServiceInvalidException;
import cn.ch1tanda.event.manager.framework.RedisManager;
import cn.ch1tanda.event.service.generic.email.EmailService;
import cn.ch1tanda.event.service.user.UserService;
import cn.ch1tanda.event.service.user.constant.UserConstants;
import cn.ch1tanda.event.utils.exception.AssertUtils;
import cn.ch1tanda.event.utils.variable.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@Component
public class UserServiceImpl implements UserService {

    @Resource
    private EmailService emailService;

    @Resource
    private RedisManager redisManager;

    @Override
    public String registerUser(String username, String password) {
        return null;
    }

    @Override
    public String sendEmailVerificationCode(String email) {
        AssertUtils.isNotBlank(email, "邮箱不能为空!");
        String verifyCode = generateRandomNumber();
        emailService.sendText(email, UserConstants.REGISTER_EMAIL_SUBJECT, "您的验证码为：" + verifyCode + "，有效期5分钟，请及时使用！");
        redisManager.set(UserConstants.REGISTER_EMAIL_CACHE_KEY_PREFIX + email, verifyCode, (long) (1000 * 60 * 5));
        log.info("用户注册邮箱验证码发送成功，email:{}, code:{}", email, verifyCode);
        return verifyCode;
    }

    @Override
    public Boolean verifyEmailVerificationCode(String email, String code) {
        AssertUtils.isNotBlank(email, "邮箱不能为空！");
        AssertUtils.isNotBlank(code, "验证码不能为空！");
        AssertUtils.isTrue(code.length() == 6, "验证码长度必须为6位！");
        String cacheCode = redisManager.get(UserConstants.REGISTER_EMAIL_CACHE_KEY_PREFIX + email);
        log.info("用户注册校验邮箱验证码，email:{}, code:{}, cacheCode:{}", email, code, cacheCode);
        if (StringUtils.isBlank(cacheCode)) {
            throw new ServiceInvalidException("验证码已失效！");
        }
        return cacheCode.equals(code);
    }

    /**
     * 随机生成六位数字，并转换为字符串返回
     */
    public static String generateRandomNumber () {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            // 生成随机0～1的double数字
            double random = Math.random();
            // 转换为BigDecimal
            BigDecimal randomDecimal = new BigDecimal(String.valueOf(random));
            // *10 并且舍弃小数部分
            BigDecimal integerPart = randomDecimal.multiply(new BigDecimal("10")).setScale(0, RoundingMode.DOWN);
            // append到字符串后面
            sb.append(integerPart);
        }
        return sb.toString();
    }

}
