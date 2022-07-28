package cn.ch1tanda.event.aop;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Aspect
@Component
@Order(2)
public class ControllerLogAspect {

    @Resource
    private HttpServletRequest request;

    @Pointcut(value = "execution(* cn.ch1tanda.event.controller..*.*(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around (ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Exception e) {
            log.info("Exception! Request path:{}, args:{}"
                    , request.getRequestURI()
                    , JSONObject.toJSONString(joinPoint.getArgs())
                    , e);
            throw e;
        }
        log.info("Request path:{}, args:{}, response:{}"
                , request.getRequestURI()
                , JSONObject.toJSONString(joinPoint.getArgs())
                , JSONObject.toJSONString(result));
        return result;
    }
}
