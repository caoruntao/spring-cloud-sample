package com.crt.spring.cloud.ribbon.registrar;

import com.crt.spring.cloud.ribbon.annotation.DiyFeignClient;
import com.crt.spring.cloud.ribbon.annotation.EnableDiyFeignClients;
import com.crt.spring.cloud.ribbon.invocationhandler.DiyFeignInvocationHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Reed
 * @date 2020/9/16 9:47 上午
 */
public class DiyFeignClientsRegistrar implements ImportBeanDefinitionRegistrar, BeanFactoryAware {
    private BeanFactory beanFactory;


    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        // 获取@EnableDiyFeignClients中clients值
        Map<String, Object> annotationAttributes =
                importingClassMetadata.getAnnotationAttributes(EnableDiyFeignClients.class.getName());
        Class<?>[] clients = (Class<?>[]) annotationAttributes.get("clients");
        // 筛选是否是接口，筛选是否被FeignClient标注
        List<Class<?>> classList = Stream.of(clients).filter(client -> {
            return client.isInterface() && client.isAnnotationPresent(DiyFeignClient.class);
        }).collect(Collectors.toList());
        // 生成代理类
        ClassLoader classLoader = importingClassMetadata.getClass().getClassLoader();
        classList.forEach(clazz -> {
            DiyFeignClient annotation = clazz.getAnnotation(DiyFeignClient.class);
            String serviceName = annotation.value();
            Object proxy = Proxy.newProxyInstance(classLoader, new Class[]{clazz}, new DiyFeignInvocationHandler(serviceName, beanFactory));
            String proxyName = "DiyProxy." + serviceName;
            // 注册
            if(registry instanceof SingletonBeanRegistry){
                SingletonBeanRegistry beanRegistry = (SingletonBeanRegistry) registry;
                beanRegistry.registerSingleton(proxyName, proxy);
            }
        });


    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
