package com.crt.spring.cloud.hystrix.annotation;

import java.lang.annotation.*;

/**
 * @author crt
 * @date 2020/9/11 10:36 上午
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TimeOutCircuitBreak {
    /**
     * 超时时间
     * @return
     */
    long value();
}
