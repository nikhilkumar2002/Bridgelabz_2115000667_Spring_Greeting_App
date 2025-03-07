package com.MyGreetingApp.backend.service;

import com.MyGreetingApp.backend.dto.AuthUserDTO;
import com.MyGreetingApp.backend.dto.LoginDTO;
import com.MyGreetingApp.backend.model.AuthUser;
import com.MyGreetingApp.backend.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private AuthUserRepository authUserRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String registerUser(AuthUserDTO authUserDTO) {
        if (authUserRepository.existsByEmail(authUserDTO.getEmail())) {
            return "Email is already in use.";
        }

        AuthUser authUser = new AuthUser();
        authUser.setFirstName(authUserDTO.getFirstName());
        authUser.setLastName(authUserDTO.getLastName());
        authUser.setEmail(authUserDTO.getEmail());
        authUser.setPassword(authUserDTO.getPassword());
        authUserRepository.save(authUser);

        return "User registered successfully!";
    }

    public String loginUser(LoginDTO loginDTO) {
        AuthUser authUser = authUserRepository.findByEmail(loginDTO.getEmail());
        if (authUser != null && passwordEncoder.matches(loginDTO.getPassword(), authUser.getPassword())) {
            return "Login successful!";
        }
        return "Invalid email or password.";
    }
}
