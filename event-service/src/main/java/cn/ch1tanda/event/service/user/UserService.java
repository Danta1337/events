package cn.ch1tanda.event.service.user;

import cn.ch1tanda.event.model.UserDO;

import java.util.List;

/**
 * 用户信息服务
 */
public interface UserService {

    /**
     * 根据username获取用户进行鉴权
     * @param username 用户名
     * @return 用户信息
     */
    UserDO auth(String username);

    /**
     * 根据用户名获取权限
     * @param username 用户名
     * @return 用户拥有的可访问资源
     */
    List<String> getAuthorities(String username);

    /**
     * 注册
     * @param req 用户注册信息
     * @return 是否注册成功
     */
    boolean register(UserDO req);

    /**
     * 找回密码
     * @param email 用户注册邮箱
     * @param newPassword 使用Spring Security的PasswordEncoder加密后的密码
     * @return 返回重新设置密码结果
     */
    Boolean retrievePassword(String email, String newPassword);

    /**
     * 发送邮箱验证码
     * @param email 注册邮箱
     * @return 生成的6位随机码
     */
    String sendEmailVerificationCode(String email);

    /**
     * 校验用户所填验证码是否正确
     * @param email 注册邮箱
     * @param code 验证码
     * @return 是否正确
     */
    Boolean verifyEmailVerificationCode(String email, String code);
}
