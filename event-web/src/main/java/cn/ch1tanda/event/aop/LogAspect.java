package cn.ch1tanda.event.aop;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogAspect {

    @Pointcut(value = "execution(* cn.ch1tanda.event.controller..*.*(..)) || execution(* cn.ch1tanda.event.manager..*.*(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around (ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Request method:{}, args:{}", joinPoint.getSignature(), JSONObject.toJSONString(joinPoint.getArgs()));
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Exception e) {
            log.info("Exception method:{}", joinPoint.getSignature(), e);
            throw e;
        }
        log.info("Response {}", JSONObject.toJSONString(result));
        return result;
    }
}
