package com.crt.springcloudeurekaclient.service;

import com.crt.springcloudeurekaclient.domain.User;
import com.crt.springcloudeurekaclient.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean saveUser(User user){
        return userRepository.saveUser(user);
    }

    @Override
    public Collection<User> findAll(){
        return userRepository.findAll();
    }
}
