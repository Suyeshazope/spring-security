package com.spring_security.lesson2.Services;

import com.spring_security.lesson2.entity.Users;
import com.spring_security.lesson2.repository.UserRepository;
import com.spring_security.lesson2.security.SecurityUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class  JpaUserDetailService implements UserDetailsService {
    private final UserRepository userRepository ;

    @Override
    public UserDetails loadUserByUsername(String username){
        var users = userRepository.findUsersByUsername(username) ;
        return users.map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found " + username));
    }
}
