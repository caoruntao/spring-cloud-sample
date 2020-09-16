package com.crt.spring.cloud.hystrix.aspect;

import com.crt.spring.cloud.hystrix.annotation.SemaphoreCircuitBreak;
import com.crt.spring.cloud.hystrix.annotation.TimeOutCircuitBreak;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.*;

/**
 * @author crt
 * @date 2020/9/11 8:43 上午
 */
@Aspect
@Component
public class ControllerAspect {
    private ExecutorService executorService = Executors.newFixedThreadPool(5);
    private Semaphore semaphore;

    @Around("execution(* com.crt.spring.cloud.hystrix.controller.Controller.advancedSay(..) )")
    public Object limite(ProceedingJoinPoint proceedingJoinPoint){
        return doInvoke(proceedingJoinPoint, 100);
    }

    @Around("execution(* com.crt.spring.cloud.hystrix.controller.Controller.advancedSayPlus(..))")
    public Object limitePlus(ProceedingJoinPoint proceedingJoinPoint){
        long timeOut = 0;
        // 获取方法上的注解
        if(proceedingJoinPoint instanceof MethodInvocationProceedingJoinPoint){
            MethodInvocationProceedingJoinPoint methodInvocationProceedingJoinPoint = (MethodInvocationProceedingJoinPoint)proceedingJoinPoint;
            MethodSignature methodSignature = (MethodSignature)methodInvocationProceedingJoinPoint.getSignature();
            Method method = methodSignature.getMethod();
            TimeOutCircuitBreak methodAnnotation = method.getAnnotation(TimeOutCircuitBreak.class);
            timeOut = methodAnnotation.value();
        }
        return doInvoke(proceedingJoinPoint, timeOut);
    }

    // 对加了@SemaphoreCircuitBreak的方法进行AOP
    @Pointcut("@annotation(semaphoreCircuitBreak)")
    public void semaphoreCircuitBreakPoint(SemaphoreCircuitBreak semaphoreCircuitBreak){}

    @Around("semaphoreCircuitBreakPoint(semaphoreCircuitBreak)")
    public Object limitePlus2(ProceedingJoinPoint proceedingJoinPoint, SemaphoreCircuitBreak semaphoreCircuitBreak){
        int count = semaphoreCircuitBreak.value();
        if(semaphore == null){
            semaphore = new Semaphore(count);
        }

        if(semaphore.tryAcquire()){
            try {
                Thread.sleep(1000);
                return doInvoke(proceedingJoinPoint, 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
        return errorSay("server rejuct");
    }

    private Object doInvoke(ProceedingJoinPoint proceedingJoinPoint, long timeOut){
        Future<Object> future = executorService.submit(() -> {
            Object resultValue = null;
            try {
                resultValue = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            return resultValue;
        });

        Object result = null;
        try {
            result = future.get(timeOut, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            future.cancel(true);
            return errorSay("time out");
        }
        return result;
    }

    private String errorSay(String message){
        return String.format("Error %s", message);
    }
}
