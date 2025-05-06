package com.spring_security.lesson2.security;

import com.spring_security.lesson2.entity.Authority;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public class SecurityAuthority implements GrantedAuthority {
    private final Authority authority ;

    @Override
    public String getAuthority() {
        return authority.getName();
    }
}
