package com.MyGreetingApp.backend.repository;

import com.MyGreetingApp.backend.model.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {
    boolean existsByEmail(String email);
    AuthUser findByEmail(String email);
}
