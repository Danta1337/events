package cn.ch1tanda.event.convention.response;

import java.io.Serializable;

public class DefaultResult<T> implements Result<T>, Serializable {

    private static final long serialVersionUID = -6022643742218396320L;

    private String code;

    private String message;

    private T data;

    public DefaultResult (String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public DefaultResult (String code, String message) {
        this.code = code;
        this.message = message;
    }

    public DefaultResult (T data) {
        this.data = data;
    }

    public DefaultResult () {

    }

    @Override
    public boolean isSuccess() {
        return this.code.equals("0");
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public T getData() {
        return this.data;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }
}
