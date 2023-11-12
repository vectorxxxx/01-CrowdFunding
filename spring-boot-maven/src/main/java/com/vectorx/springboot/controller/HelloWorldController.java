package com.vectorx.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: HelloWorldController
 * @author: VectorX
 * @date: 2022/8/23 23:45
 * @version: V1.0
 */
@RestController
@RequestMapping("/helloworld")
public class HelloWorldController
{
    @RequestMapping("/test")
    public String test() {
        return "Hello World!";
    }
}
