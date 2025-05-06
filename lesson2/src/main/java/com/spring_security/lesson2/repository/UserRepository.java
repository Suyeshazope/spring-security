package com.spring_security.lesson2.repository;

import com.spring_security.lesson2.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users , Integer> {

    @Query(value = """
            SELECT u from Users u where u.username = :username
            """)
    Optional<Users> findUsersByUsername(String username) ;
}
