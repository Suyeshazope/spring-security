package com.spring_security.lesson2.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class demoController {

    @GetMapping("demo")
    public String demo(){
        var u = SecurityContextHolder.getContext().getAuthentication() ;
        u.getAuthorities().forEach(a -> System.out.println(a));
        return "demo" ;
    }
}
