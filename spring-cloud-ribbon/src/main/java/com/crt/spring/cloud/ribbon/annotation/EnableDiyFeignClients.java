package com.crt.spring.cloud.ribbon.annotation;

import com.crt.spring.cloud.ribbon.registrar.DiyFeignClientsRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author crt
 * @date 2020/9/16 9:41 上午
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({DiyFeignClientsRegistrar.class})
public @interface EnableDiyFeignClients {
    Class<?>[] clients() default {};
}
