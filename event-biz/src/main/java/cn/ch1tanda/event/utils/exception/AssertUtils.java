package cn.ch1tanda.event.utils.exception;

import cn.ch1tanda.event.exception.ServiceInvalidException;
import cn.ch1tanda.event.utils.variable.StringUtils;

/**
 * 断言工具类
 */
public class AssertUtils {

    /**
     * 断言布尔表达式为真，如果为假，则抛出规约异常
     */
    public static void isTrue(Boolean b, String exceptionMessage) {
        if (!b) {
            throw new ServiceInvalidException(exceptionMessage);
        }
    }

    /**
     * 断言字符串不为空，如果为空，则抛出规约异常
     */
    public static void isNotBlank(String str, String exceptionMessage) {
        if (StringUtils.isBlank(str)) {
            throw new ServiceInvalidException(exceptionMessage);
        }
    }
}
