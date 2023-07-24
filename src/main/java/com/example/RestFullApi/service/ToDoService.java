package com.example.RestFullApi.service;

import com.example.RestFullApi.entity.ToDo;
import com.example.RestFullApi.entity.UserEntity;
import java.util.List;

public interface ToDoService {
    List<ToDo> getToDosByUser(UserEntity user);
    ToDo getToDoById(Long id);
    ToDo createToDo(ToDo todo);
    ToDo updateToDo(ToDo todo);
    void deleteToDoById(Long id);

}
