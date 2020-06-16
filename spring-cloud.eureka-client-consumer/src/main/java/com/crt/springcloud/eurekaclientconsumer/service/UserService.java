package com.crt.springcloud.eurekaclientconsumer.service;

import com.crt.springcloud.eurekaclientconsumer.domain.User;
import org.springframework.stereotype.Service;

import java.util.Collection;

public interface UserService {

    boolean saveUser(User user);

    Collection<User> findAll();
}
