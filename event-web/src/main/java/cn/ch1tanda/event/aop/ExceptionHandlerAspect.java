package cn.ch1tanda.event.aop;

import cn.ch1tanda.event.convention.response.Results;
import cn.ch1tanda.event.exception.ServiceException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 异常处理切面
 */
@Aspect
@Component
@Order(1)
public class ExceptionHandlerAspect {

    /**
     * 被@RequestBody修饰的所有controller包下的方法
     */
    @Pointcut(value = "execution(* cn.ch1tanda.event.controller..*.*(..)) && @annotation(org.springframework.web.bind.annotation.ResponseBody)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object exceptionHandler(ProceedingJoinPoint joinPoint) {
        Object result;
        try {
            result = joinPoint.proceed();
        } catch (ServiceException e) {
            result = Results.failure(e.getCode(), e.getMessage());
        } catch (Throwable e) {
            result = Results.failure("E_1", e.getMessage());
        }
        return result;
    }
}
