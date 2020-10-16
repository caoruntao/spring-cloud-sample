package com.crt.springcloudserviceprovider.service.impl;

import com.crt.springcloudserviceprovider.mapper.PeopleMapper;
import com.crt.springcloudserviceprovider.pojo.People;
import com.crt.springcloudserviceprovider.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Reed
 * @date 2020/10/16 9:31 上午
 */
@Service
// 配置中心动态刷新
@RefreshScope
public class PeopleServiceImpl implements PeopleService {
    @Autowired
    private PeopleMapper peopleMapper;

    @Override
    public People find(Integer id){
        return peopleMapper.find(id);
    }

    @Override
    public List<People> findAll(){
        return peopleMapper.findAll();
    }

    @Override
    @Transactional
    public boolean addPeople(People people){
        int i = peopleMapper.addPeople(people);
        if(i == 1){
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean deletePeople(Integer id){
        int i = peopleMapper.deletePeople(id);
        if(i == 1){
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean updatePeople(People people){
        int i = peopleMapper.updatePeople(people);
        if(i == 1){
            return true;
        }
        return false;
    }

    @Override
    public List<People> test(Integer id){
        return peopleMapper.test(id);
    }
}
