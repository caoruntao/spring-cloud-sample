package com.crt.springcloudserviceprovider.service;

import com.crt.springcloudserviceprovider.mapper.PeopleMapper;
import com.crt.springcloudserviceprovider.pojo.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Reed
 * @date 2020/10/16 9:31 上午
 */
public interface PeopleService {
    public People find(Integer id);

    public List<People> findAll();

    public boolean addPeople(People people);

    public boolean deletePeople(Integer id);

    public boolean updatePeople(People people);

    public List<People> test(Integer id);
}
