package com.cienet.zheng.stock.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ServiceExceptionAspect {
    @Pointcut("execution(* com.cienet.zheng.stock.services.StockServiceImpl.*(..))")
    public void exceptionHandler(){
    }

    @AfterThrowing(throwing="ex", pointcut = "exceptionHandler()")
    public void afterThrowing(Throwable ex){
        if (ex instanceof Exception) {
            log.error("get Exception: caused: {}, message: {}", ex.getCause(), ex.getMessage());
        }
    }
}
