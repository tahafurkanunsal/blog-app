package com.tahafurkan.sandbox.blogapplication.controller;

import com.tahafurkan.sandbox.blogapplication.payload.JwtAuthResponse;
import com.tahafurkan.sandbox.blogapplication.payload.LoginDto;
import com.tahafurkan.sandbox.blogapplication.payload.RegisterDto;
import com.tahafurkan.sandbox.blogapplication.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto) {
        String token = authService.login(loginDto);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }

    @PostMapping(value = {"/register", "signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}