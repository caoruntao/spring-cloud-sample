package com.crt.springcloudeurekaclient.service;

import com.crt.springcloudeurekaclient.domain.User;
import org.springframework.stereotype.Service;

import java.util.Collection;

public interface UserService {

    boolean saveUser(User user);

    Collection<User> findAll();
}
