package com.crt.spring.cloud.hystrix.annotation;

import java.lang.annotation.*;

/**
 * @author crt
 * @date 2020/9/11 10:55 上午
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SemaphoreCircuitBreak {
    /**
     * 熔断阈值
     * @return
     */
    int value();
}
