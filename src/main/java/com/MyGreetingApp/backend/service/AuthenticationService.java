package com.MyGreetingApp.backend.service;

import com.MyGreetingApp.backend.dto.AuthUserDTO;
import com.MyGreetingApp.backend.dto.LoginDTO;
import com.MyGreetingApp.backend.dto.LoginResponseDto;
import com.MyGreetingApp.backend.model.AuthUser;
import com.MyGreetingApp.backend.repository.AuthUserRepository;
import com.MyGreetingApp.backend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private AuthUserRepository authUserRepository;
    @Autowired
    private JwtUtil jwtUtil;


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

    public LoginResponseDto loginUser(LoginDTO loginDto) {
        LoginResponseDto response = new LoginResponseDto();
        String token = jwtUtil.generateToken(loginDto.getEmail());

        if (token != null) {
            response.setMessage("Login successful!");
            response.setToken(token);
        } else {
            response.setMessage("Invalid email or password.");
            response.setToken(null);
        }

        return response;
    }





}
