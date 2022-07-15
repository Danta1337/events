package cn.ch1tanda.event.service.user.constant;

/**
 * 用户相关常量类
 */
public class UserConstants {

    /**
     * 注册时邮箱验证码发送主题
     */
    public static final String REGISTER_EMAIL_SUBJECT = "【Events】请查收您的注册验证码！:)";

    /**
     * 验证码缓存前缀
     */
    public static final String REGISTER_EMAIL_CACHE_KEY_PREFIX = "event:user:register:verify:mail:";
}
