package cn.ch1tanda.event.controller.convention.response;

/**
 * 响应对象工具类
 */
public class Results {

    public static <T> Result<T> success (T data) {
        return new DefaultResult<T>("0", "success", data);
    }

    public static <T> Result<T> failure (String code, String message) {
        return new DefaultResult<T>(code, message);
    }
}
