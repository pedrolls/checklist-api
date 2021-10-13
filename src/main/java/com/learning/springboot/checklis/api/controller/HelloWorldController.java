package com.learning.springboot.checklis.api.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/say-hello")
    public String helooWorld(@RequestParam(required = false) String name) {
        return "Hello " + (StringUtils.hasText(name) ? name : "World");
    }
}