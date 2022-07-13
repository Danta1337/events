package cn.ch1tanda.event.convention.response;

/**
 * 抽象的响应对象
 * @param <T> 响应数据类型
 */
public interface Result<T>{

    String getCode();

    String getMessage();

    T getData();

    boolean isSuccess();
}
