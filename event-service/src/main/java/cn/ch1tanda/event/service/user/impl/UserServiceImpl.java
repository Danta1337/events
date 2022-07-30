package cn.ch1tanda.event.service.user.impl;

import cn.ch1tanda.event.exception.ServiceInvalidException;
import cn.ch1tanda.event.manager.framework.RedisManager;
import cn.ch1tanda.event.mapper.AuthorityMapper;
import cn.ch1tanda.event.mapper.UserMapper;
import cn.ch1tanda.event.model.UserDO;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class UserServiceImpl implements UserService {

    @Resource
    private EmailService emailService;

    @Resource
    private RedisManager redisManager;

    @Resource
    private UserMapper userMapper;

    @Resource
    private AuthorityMapper authorityMapper;

    @Override
    public UserDO auth(String username) {
        UserDO res = userMapper.selectUsernameAndPasswordByUsername(username);
        return Objects.isNull(res) ? null : res;
    }

    @Override
    public List<String> getAuthorities(String username) {
        List<String> authorities = authorityMapper.selectAuthorityByUsername(username);
        return Objects.isNull(authorities) ? new ArrayList<>() : authorities;
    }

    @Override
    public boolean register(UserDO user) {
        UserDO res = userMapper.selectUsernameAndPasswordByUsername(user.getUsername());
        if (Objects.nonNull(res)) {
            throw new ServiceInvalidException("Username already exists");
        }

        Date now = new Date();

        user.setGmtCreated(now);
        user.setGmtModified(now);
        user.setEnabled(false);
        user.setRemark("");

        try {
            int insert = userMapper.insert(user);
            return insert == 1;
        } catch (Exception e) {
            log.info("An exception occurred when registering", e);
            return false;
        }
    }

    @Override
    public Boolean retrievePassword(String email, String newPassword) {
        AssertUtils.isNotBlank(email, "Email can not be blank!");
        AssertUtils.isNotBlank(newPassword, "New password can not be blank!");
        UserDO user = userMapper.selectUserByEmail(email);
        AssertUtils.isTrue(Objects.nonNull(user), "Can not find user by email!");
        user.setPassword(newPassword);
        int updateResult = userMapper.updateByPrimaryKey(user);
        return updateResult == 1;
    }

    @Override
    public String sendEmailVerificationCode(String email) {
        AssertUtils.isNotBlank(email, "Email can not be blank!");
        String verifyCode = generateRandomNumber();
        emailService.sendText(email, UserConstants.REGISTER_EMAIL_SUBJECT, "您的验证码为：" + verifyCode + "，有效期5分钟，请及时使用！");
        redisManager.set(UserConstants.REGISTER_EMAIL_CACHE_KEY_PREFIX + email, verifyCode, (long) (1000 * 60 * 5));
        log.info("用户注册邮箱验证码发送成功，email:{}, code:{}", email, verifyCode);
        return verifyCode;
    }

    @Override
    public Boolean verifyEmailVerificationCode(String email, String code) {
        AssertUtils.isNotBlank(email, "Email can not be blank！");
        AssertUtils.isNotBlank(code, "Verification code can not be blank！");
        AssertUtils.isTrue(code.length() == 6, "Verification code length must be 6！");
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
