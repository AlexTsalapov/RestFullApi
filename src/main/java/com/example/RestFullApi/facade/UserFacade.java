package com.example.RestFullApi.facade;

import com.example.RestFullApi.dto.AuthRequest;
import org.springframework.security.core.userdetails.UserDetails;


public interface UserFacade  {
    void createUser(AuthRequest authRequest) throws Exception;
    UserDetails loadUserByUsername(String username);

}
