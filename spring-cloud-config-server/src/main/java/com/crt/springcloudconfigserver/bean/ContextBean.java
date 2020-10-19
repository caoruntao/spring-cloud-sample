package com.crt.springcloudconfigserver.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Component
public class ContextBean implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @PostConstruct
    public void init(){
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("static/template.yml");
//        Resource contextResource = applicationContext.getResource("static/template.yml");
        Yaml yaml = new Yaml();
        Map load = (Map)yaml.load(resourceAsStream);
        for(Object key : load.keySet()){
            Map oneKey = (Map)load.get(key);
            for(Object key1 : load.keySet()) {
                System.err.println(key + ":" + key1);
            }
        }

        applicationContext.getEnvironment().getProperty("");
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
