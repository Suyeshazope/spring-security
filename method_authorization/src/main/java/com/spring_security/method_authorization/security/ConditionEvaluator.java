package com.spring_security.method_authorization.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ConditionEvaluator {

    public boolean condition(){
        var a = SecurityContextHolder.getContext().getAuthentication();
        return true ;
    }
}
