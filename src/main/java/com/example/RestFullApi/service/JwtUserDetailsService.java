package com.example.RestFullApi.service;

import com.example.RestFullApi.entity.UserEntity;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface JwtUserDetailsService extends UserDetailsService {
    void createUser(UserEntity user) throws Exception;
    UserEntity loadUserByUsername(String username);
}
