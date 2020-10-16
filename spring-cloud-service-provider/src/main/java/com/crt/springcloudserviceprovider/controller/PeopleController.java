package com.crt.springcloudserviceprovider.controller;

import com.crt.springcloudserviceprovider.pojo.People;
import com.crt.springcloudserviceprovider.pojo.ResultData;
import com.crt.springcloudserviceprovider.service.PeopleService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeoutException;

/**
 * @author Reed
 * @date 2020/10/16 9:39 上午
 */
@RestController
public class PeopleController {
    @Autowired
    private PeopleService peopleService;
    private Random random = new Random();

    @HystrixCommand(fallbackMethod = "findError",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
                            value = "100")
            })
    @GetMapping("/people/{id}")
    public ResultData<People> find(@PathVariable Integer id) {
        int sleepTime = random.nextInt(200);
        System.err.println("sleepTime = " + sleepTime);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        People findedPeople = peopleService.find(id);
        return ResultData.success(findedPeople);
    }

    private ResultData<People> findError(Integer id) {
        return ResultData.error(500, null);
    }

    /**
     * TODO 信号灯没起作用，需要排查
     * @return
     */
    @HystrixCommand(fallbackMethod = "findAllError",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy",
                            value = "SEMAPHORE"),
                    @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests",
                            value = "1")
            })
    @GetMapping("/people/all")
    public ResultData<List<People>> findAll() {
        List<People> allPeople = peopleService.findAll();
        return ResultData.success(allPeople);
    }

    private ResultData<List<People>> findAllError() {
        return ResultData.error(500, null);
    }

    @HystrixCommand(fallbackMethod = "addPeopleError",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
                            value = "100")
            })
    @PostMapping("/people")
    public ResultData<People> addPeople(@RequestBody People people) {
        int sleepTime = random.nextInt(200);
        System.err.println("sleepTime = " + sleepTime);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean isSuccess = peopleService.addPeople(people);
        if (isSuccess) {
            return ResultData.success(people);
        }
        return ResultData.error(500, null);
    }

    private ResultData<People> addPeopleError(People people) throws TimeoutException {
        throw new TimeoutException();
    }

    @HystrixCommand(fallbackMethod = "updatePeopleError",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
                            value = "100")
            })
    @PutMapping("/people")
    public ResultData<People> updatePeople(@RequestBody People people) {
        int sleepTime = random.nextInt(200);
        System.err.println("sleepTime = " + sleepTime);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean isSuccess = peopleService.updatePeople(people);
        if (isSuccess) {
            return ResultData.success(people);
        }
        return ResultData.error(500, null);
    }

    private ResultData<People> updatePeopleError(People people) {
        return ResultData.error(500, null);
    }

    @HystrixCommand(fallbackMethod = "deletePeopleError",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
                            value = "100")
            })
    @DeleteMapping("/people/{id}")
    public ResultData<Integer> deletePeople(@PathVariable Integer id) {
        int sleepTime = random.nextInt(200);
        System.err.println("sleepTime = " + sleepTime);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean isSuccess = peopleService.deletePeople(id);
        if (isSuccess) {
            return ResultData.success(id);
        }
        return ResultData.error(500, null);
    }

    private ResultData<Integer> deletePeopleError(Integer id) {
        return ResultData.error(500, null);
    }
}
