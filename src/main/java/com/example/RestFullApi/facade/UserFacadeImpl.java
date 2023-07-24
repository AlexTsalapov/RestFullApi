package com.example.RestFullApi.facade;

import com.example.RestFullApi.dto.AuthRequest;
import com.example.RestFullApi.entity.UserEntity;
import com.example.RestFullApi.mapper.UserMapper;
import com.example.RestFullApi.service.JwtUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public class UserFacadeImpl implements UserFacade {
    private final JwtUserDetailsService userService;
    private final UserMapper userMapper;

    public UserFacadeImpl(JwtUserDetailsService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    public void createUser(AuthRequest authRequest) throws Exception {
        UserEntity user = userMapper.toEntity(authRequest);
        userService.createUser(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity userEntity =  userService.loadUserByUsername(username);
        return userMapper.toUserDetails(userEntity);
    }

}
