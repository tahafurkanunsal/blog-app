package com.tahafurkan.sandbox.blogapplication.service;

import com.tahafurkan.sandbox.blogapplication.payload.LoginDto;

public interface AuthService {

    String login(LoginDto loginDto);
}
