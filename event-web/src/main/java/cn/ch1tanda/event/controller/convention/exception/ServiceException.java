package cn.ch1tanda.event.controller.convention.exception;

/**
 * 统一的服务异常
 */
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = -4157946099632793481L;

    private String code;

    ServiceException (String code, String message) {
        super(message);
        this.code = code;
    }
}
