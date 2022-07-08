package cn.ch1tanda.event.exception;

/**
 * 服务不合法异常
 */
public class ServiceInvalidException extends ServiceException{

    private static final long serialVersionUID = -2226699590698692774L;

    public ServiceInvalidException(String message) {
        super("C_1", message);
    }
}
