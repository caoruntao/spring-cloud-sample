package com.crt.springcloudconfigclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfController {

    private final Environment environment;

    @Autowired
    public ConfController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping("/echo/env/{name}")
    public String getEnvironment(@PathVariable String name){
        String value = environment.getProperty(name);
        return value;
    }

}
