package cn.ch1tanda.event.exception;

/**
 * 统一的服务异常
 */
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = -4157946099632793481L;

    private String code;

    public ServiceException (String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
