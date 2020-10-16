package com.crt.springcloudserviceconsumer.controller;

import com.crt.springcloudserviceconsumer.pojo.People;
import com.crt.springcloudserviceconsumer.pojo.ResultData;
import com.crt.springcloudserviceconsumer.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Reed
 * @date 2020/10/16 9:39 上午
 */
@RestController
public class PeopleController {
    @Autowired
    private PeopleService peopleService;

    @GetMapping("/client/people/{id}")
    public ResultData<People> find(@PathVariable Integer id) {
        return peopleService.find(id);
    }

    @GetMapping("/client/people/all")
    public ResultData<List<People>> findAll() {
        return peopleService.findAll();
    }

    @PostMapping("/client/people")
    public ResultData<People> addPeople(@RequestBody People people) {
        return peopleService.addPeople(people);
    }

    @PutMapping("/client/people")
    public ResultData<People> updatePeople(@RequestBody People people) {
        return peopleService.updatePeople(people);
    }

    @DeleteMapping("/client/people/{id}")
    public ResultData<Integer> deletePeople(@PathVariable Integer id) {
        return peopleService.deletePeople(id);
    }
}
