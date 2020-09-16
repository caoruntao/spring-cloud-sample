package com.crt.spring.cloud.hystrix.controller;

import com.crt.spring.cloud.hystrix.annotation.SemaphoreCircuitBreak;
import com.crt.spring.cloud.hystrix.annotation.TimeOutCircuitBreak;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author crt
 * @date 2020/9/11 1:36 下午
 */
@RestController
public class Controller {
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private Random random = new Random();

    /**
     * 初级版本
     * 无容错处理
     * 超时后业务代码还会执行(看需求是否需要执行)
     * 代码耦合性高
     *
     * @param message
     * @return
     */
    @GetMapping("/elementary/say")
    public String elementarySay(String message){
        Future<String> stringFuture = executorService.submit(() -> {
            return doElementarySay(message);
        });
        String result = null;
        try {
            result = stringFuture.get(100, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 初级版本
     * 超时后业务代码还会执行(看需求是否需要执行)
     * 代码耦合性高
     *
     * @param message
     * @return
     */
    @GetMapping("/elementary/say/plus")
    public String elementarySayPlus(String message){
        Future<String> stringFuture = executorService.submit(() -> {
            return doElementarySay(message);
        });
        String result = null;
        try {
            result = stringFuture.get(100, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            return errorSay(message);
        }
        return result;
    }

    /**
     * 初级版本
     * 代码耦合性高
     * @param message
     * @return
     */
    @GetMapping("/elementary/say/plus2")
    public String elementarySayPlus2(String message){
        Future<String> stringFuture = executorService.submit(() -> {
            return doElementarySay(message);
        });
        String result = null;
        try {
            result = stringFuture.get(100, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            stringFuture.cancel(true);
            return errorSay(message);
        }
        return result;
    }

    /**
     * 中级版本
     * 拦截器实现
     * @param message
     * @return
     * @throws Exception
     */
    @GetMapping("/middle/say")
    public String middleSay(String message) throws Exception {
        Future<String> future = executorService.submit(() -> {
            return doElementarySay(message);
        });

        String result = null;
        try {
            result = future.get(100, TimeUnit.MILLISECONDS);
        }  catch (Exception e) {
            future.cancel(true);
            throw e;
        }

        return result;
    }

    /**
     * 高级版本
     *  AOP实现
     * @param message
     * @return
     * @throws Exception
     */
    @GetMapping("/advanced/say")
    public String advancedSay(String message) throws Exception {
        return doElementarySay(message);
    }

    /**
     *  高级版本
     *  AOP+注解(超时时间)
     *
     * @param message
     * @return
     * @throws Exception
     */
    @TimeOutCircuitBreak(100)
    @GetMapping("/advanced/say/plus")
    public String advancedSayPlus(String message) throws Exception {
        return doElementarySay(message);
    }

    /**
     * 高级版本
     * AOP+注解(信号量)
     *
     * @param message
     * @return
     * @throws Exception
     */
    @SemaphoreCircuitBreak(1)
    @GetMapping("/advanced/say/plus2")
    public String advancedSayPlus2(String message) throws Exception {
        return doElementarySay(message);
    }

    @HystrixCommand(
            fallbackMethod = "errorSay",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
                            value = "100")
            })
    @GetMapping("/hystrix/say")
    public String hystrixSay(String message) throws Exception {
        return doElementarySay(message);
    }

    public String doElementarySay(String message) throws InterruptedException {
        int randomInt = random.nextInt(200);
        System.out.println(String.format("doSay() cost %d ms", randomInt));
        Thread.sleep(randomInt);

        String str = String.format("Hello %s", message);
        System.out.println(str);
        return str;
    }

    public String errorSay(String message){
        return String.format("Error %s", message);
    }
}
