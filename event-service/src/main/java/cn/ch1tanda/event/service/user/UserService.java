package cn.ch1tanda.event.service.user;

/**
 * 用户信息服务
 */
public interface UserService {
    /**
     * 用户注册
     * @param username 用户名
     * @param password 密码
     * @return 注册结果
     */
    String registerUser(String username, String password);

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
