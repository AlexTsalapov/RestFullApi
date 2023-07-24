package com.example.RestFullApi.mapper;

import com.example.RestFullApi.dto.AuthRequest;
import com.example.RestFullApi.entity.UserEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserMapper {
    private final BCryptPasswordEncoder passwordEncoder;

    public UserMapper( BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity toEntity(AuthRequest authRequest) {
        UserEntity user = new UserEntity();
        user.setUsername(authRequest.getUsername());
        user.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        return user;
    }
    public UserDetails toUserDetails(UserEntity userEntity){
        return new User(userEntity.getUsername(), userEntity.getPassword(),new ArrayList<>());
    }

}
