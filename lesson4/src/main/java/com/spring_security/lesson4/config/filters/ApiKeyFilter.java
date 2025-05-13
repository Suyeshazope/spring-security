package com.spring_security.lesson4.config.filters;

import com.spring_security.lesson4.config.authentications.ApiKeyAuthentication;
import com.spring_security.lesson4.config.managers.CustomAuthenticationManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;

@AllArgsConstructor
public class ApiKeyFilter extends OncePerRequestFilter {

    private final String key ;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        CustomAuthenticationManager customAuthenticationManager = new CustomAuthenticationManager(key) ;

        var requestKey = request.getHeader("x-api-key") ;

        if("null".equals(requestKey) || requestKey == null){
            filterChain.doFilter(request , response);
        }
        var auth = new ApiKeyAuthentication(requestKey) ;

        try{
            var a = customAuthenticationManager.authenticate(auth) ;
            if(a.isAuthenticated()){
                SecurityContextHolder.getContext().setAuthentication(a);
                filterChain.doFilter(request , response);
            }
        } catch (AuthenticationException e){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

    }
}
