package com.cienet.zheng.stock.aspects;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class HttpAspect {

    @Pointcut("execution(* com.cienet.zheng.stock.controllers.StockController.*(..))")
    public void logger(){
    }

    @Before("logger()")
    public void log(JoinPoint joinPoint){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        log.info("url={}, ip={}, method={}, class_method={}",
                request.getRequestURL(),
                request.getRemoteAddr(),
                request.getMethod(),
                joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
    }

    @AfterReturning(returning = "o",pointcut = "logger()")
    public void after(Object o){
        log.info("response={}", JSON.toJSONString(o));
    }
}
