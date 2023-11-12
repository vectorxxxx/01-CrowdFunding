package com.vectorx.springboot.springbootplugin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: HelloWorldController
 * @author: VectorX
 * @date: 2022/8/24 21:33
 * @version: V1.0
 */
@RestController
@RequestMapping("/helloworld")
public class HelloWorldController
{
    @GetMapping("/test")
    public String test(){
        return "Hello World!";
    }
}
