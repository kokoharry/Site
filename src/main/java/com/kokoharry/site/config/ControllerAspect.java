//package com.kokoharry.site.config;
//
//import com.google.common.base.Stopwatch;
//import com.google.common.collect.Lists;
//import com.kokoharry.site.system.bean.Respond;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.TimeUnit;
//
//@Component
//@Aspect
//@Slf4j
//public class ControllerAspect {
//
//    @Around("execution(public com.kokoharry.site.system.bean.Respond com..*.controller..*.*(..))")
//    public Object handleControllerMethod(ProceedingJoinPoint pjp) {
//        Stopwatch stopwatch = Stopwatch.createStarted();
//
//        Respond<?> resultEntity;
//        try {
//            String methodName = pjp.getSignature().getName();
//            log.info("执行" + methodName + "开始:  参数：" + Lists.newArrayList(pjp.getArgs()).toString());
//            resultEntity = (Respond<?>) pjp.proceed(pjp.getArgs());
//            log.info("执行" + methodName + "结束: 返回值：" + resultEntity.toString() + "\n耗时" + stopwatch.stop().elapsed(TimeUnit.MILLISECONDS) + "(毫秒).");
//        } catch (Throwable throwable) {
//            resultEntity = handlerException(pjp, throwable);
//        }
//
//        return resultEntity;
//    }
//
//    private Respond<?> handlerException(ProceedingJoinPoint pjp, Throwable e) {
//        Respond<?> resultEntity = null;
//        if (e instanceof RuntimeException) {
//            log.error("RuntimeException{方法：" + pjp.getSignature() + "， 参数：" + pjp.getArgs() + ",异常：" + e.getMessage() + "}", e);
//            resultEntity = new Respond(500,e.getMessage());
//        } else {
//            log.error("异常{方法：" + pjp.getSignature() + "， 参数：" + pjp.getArgs() + ",异常：" + e.getMessage() + "}", e);
//            resultEntity = new Respond(510,e.getMessage());
//        }
//
//        return resultEntity;
//    }
//}