package cn.ch1tanda.event.aop;

import cn.ch1tanda.event.mapper.RequestHistoryMapper;
import cn.ch1tanda.event.model.RequestHistoryDO;
import cn.ch1tanda.event.utils.http.HttpUtils;
import cn.ch1tanda.event.utils.variable.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求者IP记录切面
 */
@Slf4j
@Aspect
@Component
public class RequestIpRecordAspect {

    @Resource
    private ThreadPoolTaskExecutor executor;

    @Resource
    private RequestHistoryMapper requestHistoryMapper;

    @Resource
    private HttpServletRequest request;

    private static final String IP_CHECK_URL = "http://ip.plyz.net/ip.ashx";

    /**
     * 所有的Controller接口作为切点
     */
    @Pointcut(value = "execution(* cn.ch1tanda.event.controller..*.*(..))")
    public void pointcut() {}

    @Around(value = "pointcut()")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            String ip = request.getRemoteAddr();
            executor.execute(() -> this.saveRequestHistory(ip));
        } catch (Exception e) {
            log.info("Exception occurred when get request IP!", e);
        }
        return joinPoint.proceed();
    }

    private void saveRequestHistory (String ip) {
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        String ipDetails = HttpUtils.GET(IP_CHECK_URL, params);
        if (StringUtils.isBlank(ipDetails)) {
            return ;
        }
        String[] strArray1 = ipDetails.split("\\|");
        if (strArray1.length < 2) {
            return ;
        }
        String[] strArray2 = strArray1[1].split(" ");
        RequestHistoryDO requestHistory = new RequestHistoryDO();
        requestHistory.setIp(ip);
        requestHistory.setCountry(strArray2[0]);
        requestHistory.setProvince(strArray2[1]);
        requestHistory.setCity(strArray2[2]);
        requestHistory.setISP(strArray2[3]);
        requestHistoryMapper.insert(requestHistory);
    }
}
