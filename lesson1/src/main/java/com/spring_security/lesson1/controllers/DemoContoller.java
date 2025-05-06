package com.spring_security.lesson1.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoContoller {

    @GetMapping("hello")
    public String hello(){
        return "Hello !!" ;
    }
}
