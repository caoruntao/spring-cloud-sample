package com.crt.spring.cloud.ribbon.invocationhandler;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.core.StandardReflectionParameterNameDiscoverer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author crt
 * @date 2020/9/15 9:39 上午
 */
public class DiyFeignInvocationHandler implements InvocationHandler {
    private String serviceName;
    private BeanFactory beanFactory;
    private StandardReflectionParameterNameDiscoverer standardReflectionParameterNameDiscoverer;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        int parameterCount = method.getParameterCount();
        Class<?>[] parameterTypes = method.getParameterTypes();
        String[] parameterNames = standardReflectionParameterNameDiscoverer.getParameterNames(method);

        if(method.isAnnotationPresent(GetMapping.class)){
            GetMapping annotation = method.getAnnotation(GetMapping.class);
            String[] value = annotation.value();
            // 访问地址
            StringBuilder stringBuilder = new StringBuilder(serviceName).append("/").append(value[0]).append("?");

            // 参数拼接
            for (int i = 0; i < parameterCount; i++) {
                String param = String.class.equals(parameterTypes) ? (String) args[i] : String.valueOf(args[i]);
                stringBuilder.append(String.format("%s=%s&", parameterNames[i], param));
            }

            RestTemplate diyRestTemplate = beanFactory.getBean("diyRestTemplate", RestTemplate.class);
            return diyRestTemplate.getForObject(stringBuilder.toString(), method.getReturnType());
        }
        return null;
    }

    public DiyFeignInvocationHandler(String serviceName,BeanFactory beanFactory) {
        this.serviceName = serviceName;
        this.beanFactory = beanFactory;
        this.standardReflectionParameterNameDiscoverer = new StandardReflectionParameterNameDiscoverer();
    }
}
