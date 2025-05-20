package com.spring_security.endpoint_authorization.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.httpBasic(Customizer.withDefaults())
//                .authorizeHttpRequests(auth -> auth.anyRequest().access(AuthorizationManagers.allOf(AuthenticatedAuthorizationManager.authenticated() ,
//                        AuthorityAuthorizationManager.hasAuthority("read"))))
                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/demo").hasAuthority("read")
                        .requestMatchers(HttpMethod.GET , "/api/*").hasAuthority("write")
                )
                .build() ;
    }

    @Bean
    public UserDetailsService userDetailsService(){
        var uds = new InMemoryUserDetailsManager() ;

        var u1 = User.withUsername("Bill")
                .password(passwordEncoder().encode("12345"))
                .authorities("read")
//                .roles("ADMIN")
                .build() ;

        var u2 = User.withUsername("harry")
                .password(passwordEncoder().encode("13579"))
                .authorities("write")
//                .roles("SUPER_ADMIN")
                .build() ;

        uds.createUser(u1);
        uds.createUser(u2);

        return uds ;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder() ;
    }
}
