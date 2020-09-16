package com.crt.spring.cloud.hystrix.conf;

import com.crt.spring.cloud.hystrix.interceptor.ErrorInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author crt
 * @date 2020/8/26 9:20 上午
 */
@Configuration
public class MyWebMVCConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(errorInterceptor());
    }

    @Bean
    public ErrorInterceptor errorInterceptor(){
        return new ErrorInterceptor();
    }
}
