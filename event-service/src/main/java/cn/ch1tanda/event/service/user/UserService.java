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
}
