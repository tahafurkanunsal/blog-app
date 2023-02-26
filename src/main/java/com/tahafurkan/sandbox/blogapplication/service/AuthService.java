package com.tahafurkan.sandbox.blogapplication.service;

import com.tahafurkan.sandbox.blogapplication.payload.LoginDto;
import com.tahafurkan.sandbox.blogapplication.payload.RegisterDto;

public interface AuthService {

    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
