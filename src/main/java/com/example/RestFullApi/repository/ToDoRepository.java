package com.example.RestFullApi.repository;

import com.example.RestFullApi.entity.ToDo;
import com.example.RestFullApi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo,Long> {
    List<ToDo> findToDoByUser(UserEntity user);
}
