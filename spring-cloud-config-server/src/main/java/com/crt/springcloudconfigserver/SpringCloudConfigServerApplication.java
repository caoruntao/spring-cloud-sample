package com.crt.springcloudconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@EnableConfigServer
public class SpringCloudConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConfigServerApplication.class, args);
    }

    //@Bean
    public EnvironmentRepository environmentRepository() {
        //自定义实现,如果无则使用GIT实现
        return (String application, String profile, String label) -> {
            Environment environment = new Environment("default", profile);

            List<PropertySource> propertySources = environment.getPropertySources();

            Map<String, Object> sources = new HashMap<>();

            sources.put("name", "crt");

            PropertySource propertySource = new PropertySource("map", sources);

            propertySources.add(propertySource);

            return environment;
        };
    }

}

