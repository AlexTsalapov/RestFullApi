package com.example.RestFullApi.service;

import com.example.RestFullApi.entity.ToDo;
import com.example.RestFullApi.entity.UserEntity;
import com.example.RestFullApi.repository.ToDoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoServiceImpl implements ToDoService {
    private final ToDoRepository toDoRepository;

    public ToDoServiceImpl(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public List<ToDo> getToDosByUser(UserEntity user) {
        return toDoRepository.findToDoByUser(user);
    }

    @Override
    public ToDo getToDoById(Long id) {
        return toDoRepository.getById(id);
    }

    public ToDo createToDo(ToDo todo) {
        return toDoRepository.save(todo);
    }

    public ToDo updateToDo(ToDo todo) {
        return toDoRepository.save(todo);
    }

    public void deleteToDoById(Long id) {
        toDoRepository.deleteById(id);
    }
}

