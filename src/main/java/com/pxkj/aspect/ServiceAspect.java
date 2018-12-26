package com.pxkj.aspect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Component
@Aspect
public class ServiceAspect {

    private static final Log log = LogFactory.getLog(ServiceAspect.class);

    @Pointcut("execution(public * com.pxkj.service.*.*(..))")
    public void aspect() {

    }

    @Before("aspect()")
    public void before(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        System.out.println("URL : " + request.getRequestURL().toString());
        System.out.println("HTTP_METHOD : " + request.getMethod());
        System.out.println("IP : " + request.getRemoteAddr());
        System.out.println("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        System.out.println("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    @After("aspect()")
    public void after(JoinPoint joinPoint) {
        log.info("aspect after " + joinPoint);
    }

    @Around("aspect()")
    public void around(JoinPoint joinPoint) {
        long start = System.currentTimeMillis();
        try {
            ((ProceedingJoinPoint) joinPoint).proceed();
            long end = System.currentTimeMillis();
            log.info("aspect around " + joinPoint + "\tUse time : " + (end - start) + " ms!");
        } catch (Throwable e) {
            long end = System.currentTimeMillis();
            log.info("aspect around " + joinPoint + "\tUse time : " + (end - start) + " ms with exception : " + e.getMessage());
        }
    }

    @AfterReturning("aspect()")
    public void afterReturn(JoinPoint joinPoint){
        log.info("aspect afterReturn " + joinPoint);
    }

    @AfterThrowing(pointcut = "aspect()", throwing = "ex")
    public void afterThrow(JoinPoint joinPoint, Exception ex){
        log.info("aspect afterThrow " + ex.getMessage());
    }

}
