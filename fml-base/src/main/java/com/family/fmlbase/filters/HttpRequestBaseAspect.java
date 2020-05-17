package com.family.fmlbase.filters;

import com.family.fmlbase.utils.IPv4Util;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Order(0)
@Aspect
@Configuration
public class HttpRequestBaseAspect {
    private static final Logger log = LoggerFactory.getLogger(HttpRequestBaseAspect.class);

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void pointcut() {}
    @Before("pointcut()")
    public void before(JoinPoint point) {
        Long begin = Long.valueOf(System.currentTimeMillis());
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

            ServletRequestAttributes attributes = (ServletRequestAttributes)requestAttributes;
            HttpServletRequest request = attributes.getRequest();
            HttpServletResponse response = attributes.getResponse();
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0L);
            String traceId = request.getHeader("traceId");
            String userId = request.getHeader("userId");
            if(StringUtils.isEmpty(userId)){
                userId ="0";
            }
            if (StringUtils.isEmpty(traceId)) {
                traceId = UUID.randomUUID().toString().replace("-", "");
            }
            MDC.put("begin", begin.toString());
            MDC.put("ip", IPv4Util.getIpAddr(request));
            MDC.put("traceId", traceId);
            MDC.put("user_id",userId);



    }

    @After("pointcut()")
    public void after(JoinPoint point) {}

    @AfterReturning(returning = "resp", pointcut = "pointcut()")
    public void aftererun(JoinPoint point, Object resp) {
        String beginString = MDC.get("begin");
        Long begin=0L;
        if(!StringUtils.isEmpty(beginString)){
            log.error("begin1_is_null,{}",beginString);
        }

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes attributes = (ServletRequestAttributes)requestAttributes;
        HttpServletRequest request = attributes.getRequest();
        long taking = System.currentTimeMillis() - begin.longValue();
        log.info("request={} user_id={} method={} taking={}", new Object[] { request.getRequestURI(), MDC.get("user_id"), request.getMethod(), Long.valueOf(taking) });
        MDC.clear();
    }
}

