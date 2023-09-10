package com.example.demo.services;

import com.example.demo.entity.User;
import com.example.demo.security.payload.AuthResponse;

public interface IUserService {
    public AuthResponse createUser(User user);
}
