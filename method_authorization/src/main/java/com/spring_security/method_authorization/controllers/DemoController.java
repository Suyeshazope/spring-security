package com.spring_security.method_authorization.controllers;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DemoController {

    @GetMapping("/demo1")
    @PreAuthorize("hasAuthority('read')")
    public String demo1(){
        return "Demo1!!!!" ;
    }

    @GetMapping("/demo2")
    @PreAuthorize("hasAnyAuthority('write' , 'read')")
    public String demo2(){
        return "Demo2!!!!" ;
    }

    @GetMapping("/demo3/{smth}")
    @PreAuthorize("""
            (#something == authentication.name) or
            hasAnyAuthority('write' , 'read')""" )
    public String demo3(@PathVariable("smth") String something){
        var a = SecurityContextHolder.getContext().getAuthentication();
        return "Something...!!" ;
    }

    @GetMapping("/demo4/{smth}")
    @PreAuthorize("@conditionEvaluator.condition()")
    public String demo4(){
        return "Something 124...!!" ;
    }

    @GetMapping("/demo5")
    @PostAuthorize("returnObject == 'DEMO 5'")
//    @PostAuthorize("returnObject == 'DEMO 51'")//mainly used to restrict the access to some returned value...
    public String demo5(){
        System.out.println(":))");
        return "DEMO 5" ;
    }

    @GetMapping("/demo6")
    @PreFilter("filterObject.contains('a')")
    public String demo6(@RequestBody List<String> strings){
        System.out.println("Strings : " + strings);
        return "DEMO 6" ;
    }

    @GetMapping("/demo7")
    @PostFilter("filterObject.contains('a')")
    public List<String> demo7(){
        List<String> list = new ArrayList<>() ;

        list.add("asdd") ;
        list.add("vvdv") ;
        list.add("svdj") ;
        list.add("asxx") ;

        return list ;
    }
}
