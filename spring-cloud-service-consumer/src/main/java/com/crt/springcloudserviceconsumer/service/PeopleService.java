package com.crt.springcloudserviceconsumer.service;

import com.crt.springcloudserviceconsumer.pojo.People;
import com.crt.springcloudserviceconsumer.pojo.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Reed
 * @date 2020/10/16 9:31 上午
 */
@FeignClient(value = "${provider.name}")
public interface PeopleService {
    @GetMapping("/people/{id}")
    ResultData<People> find(@PathVariable Integer id);

    @GetMapping("/people/all")
    ResultData<List<People>> findAll();

    @PostMapping("/people")
    ResultData<People> addPeople(People people);

    @PutMapping("/people")
    ResultData<People> updatePeople(People people);

    @DeleteMapping("/people/{id}")
    ResultData<Integer> deletePeople(@PathVariable Integer id);
}
