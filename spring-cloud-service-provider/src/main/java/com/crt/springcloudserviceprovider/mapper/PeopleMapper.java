package com.crt.springcloudserviceprovider.mapper;

import com.crt.springcloudserviceprovider.pojo.People;

import java.util.List;

/**
 * @author Reed
 * @date 2020/10/16 9:28 上午
 */
public interface PeopleMapper {
    People find(Integer id);

    List<People> findAll();

    int addPeople(People people);

    int deletePeople(Integer id);

    int updatePeople(People people);

    List<People> test(Integer id);
}
