package com.crt.springcloudeurekaclient.repository;

import com.crt.springcloudeurekaclient.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepository {
    private Map<Long, User> userMap = new ConcurrentHashMap<>(16);
    private static final AtomicLong ID_GENERATOR = new AtomicLong(0);

    public boolean saveUser(User user){
        Long id = ID_GENERATOR.getAndIncrement();
        user.setId(id);
        return userMap.putIfAbsent(id, user) == null;
    }

    public Collection<User> findAll(){
        return userMap.values();
    }
}
