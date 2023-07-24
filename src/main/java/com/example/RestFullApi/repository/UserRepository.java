package com.example.RestFullApi.repository;

import com.example.RestFullApi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByUsernameAndPassword(String username, String password);
    UserEntity findUserById(Long id);
    UserEntity findUserByUsername(String username);
}
