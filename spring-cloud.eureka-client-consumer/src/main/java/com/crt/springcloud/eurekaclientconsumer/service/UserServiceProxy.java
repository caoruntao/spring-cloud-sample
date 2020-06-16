package com.crt.springcloud.eurekaclientconsumer.service;

import com.crt.springcloud.eurekaclientconsumer.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;


/**
 * {@link UserService}
 */
@Service
public class UserServiceProxy implements UserService{
    private static final String PROVIDER_SERVER_URL_PROFIX = "http://user-service-provider/";
    private final RestTemplate restTemplate;

    @Autowired
    public UserServiceProxy(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean saveUser(User user) {
        User userValue = restTemplate.postForObject(PROVIDER_SERVER_URL_PROFIX + "user/save", user, User.class);
        return userValue == null ? false : true;
    }

    @Override
    public Collection<User> findAll() {
        return restTemplate.getForObject(PROVIDER_SERVER_URL_PROFIX + "user/findAll", Collection.class);
    }
}
