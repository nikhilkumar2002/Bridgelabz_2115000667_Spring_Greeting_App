package com.MyGreetingApp.backend.controller;

import com.MyGreetingApp.backend.dto.AuthUserDTO;
import com.MyGreetingApp.backend.dto.LoginDTO;
import com.MyGreetingApp.backend.dto.LoginResponseDto;
import com.MyGreetingApp.backend.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthUserController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody AuthUserDTO authUserDTO) {
        String response = authenticationService.registerUser(authUserDTO);
        if (response.equals("User registered successfully!")) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDto) {
        LoginResponseDto response = authenticationService.loginUser(loginDto);
        if (response.getToken() != null) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("{\"message\": \"Invalid email or password!\"}");
    }

}
