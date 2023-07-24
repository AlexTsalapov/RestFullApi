package com.example.RestFullApi.facade;

import com.example.RestFullApi.dto.ToDoDTO;

import java.util.List;

public interface ToDoFacade{
    List<ToDoDTO> getToDosByUser(String username);
    ToDoDTO createToDo(ToDoDTO toDoDTO,String username);
    ToDoDTO updateToDo(ToDoDTO toDoDTO) throws Exception;
    void deleteToDoById(Long id) throws Exception;
}
