package cn.ch1tanda.event.aop;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@Order(2)
public class ManagerLogAspect {

    @Pointcut(value = "execution(* cn.ch1tanda.event.manager..*.*(..)) || execution(* cn.ch1tanda.event.service..*.*(..))")
    public void pointCut(){}

    @Around(value = "pointCut()")
    public Object aroundAdvice (ProceedingJoinPoint joinPoint) throws Throwable {
        String[] declaringTypeNameSplit = joinPoint.getSignature().getDeclaringTypeName().split("\\.");
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Exception e) {
            log.info("Exception Method: {}, args:{}"
                    , declaringTypeNameSplit[declaringTypeNameSplit.length - 1]
                    , JSONObject.toJSONString(joinPoint.getArgs())
                    , e);
            throw e;
        }
        log.info("Method: {}, args:{}, return:{}"
                , declaringTypeNameSplit[declaringTypeNameSplit.length - 1] + "." + joinPoint.getSignature().getName()
                , JSONObject.toJSONString(joinPoint.getArgs())
                , JSONObject.toJSONString(result)
                );
        return result;
    }
}
