package com.MyGreetingApp.backend.dto;

public class LoginResponseDto {
    private String message;
    private String token;

    // Getters and Setters
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}
