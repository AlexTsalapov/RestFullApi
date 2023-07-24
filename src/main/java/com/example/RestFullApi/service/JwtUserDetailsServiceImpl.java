package com.example.RestFullApi.service;

import com.example.RestFullApi.entity.UserEntity;
import com.example.RestFullApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class JwtUserDetailsServiceImpl implements JwtUserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public JwtUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findUserByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return userEntity;
    }
    @Override
    public void createUser(UserEntity user) throws Exception {
        if ( userRepository.findUserByUsername(user.getUsername()) == null) {
            userRepository.save(user);
        } else {
            throw new Exception("User is exist");
        }
    }

}


