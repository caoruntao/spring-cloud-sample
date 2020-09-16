package com.crt.spring.cloud.ribbon.annotation;

import java.lang.annotation.*;

/**
 * @author crt
 * @date 2020/9/16 10:02 上午
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DiyFeignClient {
    String value() default "";
}
