package com.example.RestFullApi.mapper;


import com.example.RestFullApi.dto.ToDoDTO;
import com.example.RestFullApi.entity.ToDo;
import com.example.RestFullApi.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ToDoMapper {
    public ToDoDTO toDto(ToDo toDo) {
        ToDoDTO toDoDTO = new ToDoDTO();
        toDoDTO.setId(toDo.getId());
        toDoDTO.setTitle(toDo.getTitle());
        toDoDTO.setDescription(toDo.getDescription());
        toDoDTO.setCreatedAt(toDo.getCreatedAt());
        toDoDTO.setCompleted(toDo.isCompleted());
        return toDoDTO;
    }


    public ToDo toEntity(ToDoDTO toDoDTO, UserEntity user) {
        ToDo toDo = new ToDo();
        toDo.setTitle(toDoDTO.getTitle());
        toDo.setDescription(toDoDTO.getDescription());
        toDo.setCreatedAt(toDoDTO.getCreatedAt());
        toDo.setCompleted(toDoDTO.isCompleted());
        toDo.setUser(user);
        return toDo;
    }

    public ToDo updateEntity(ToDoDTO dto, ToDo existingToDo) {
        existingToDo.setTitle(dto.getTitle());
        existingToDo.setDescription(dto.getDescription());
        existingToDo.setCreatedAt(dto.getCreatedAt());
        existingToDo.setCompleted(dto.isCompleted());
        return existingToDo;
    }
    public List<ToDoDTO> toDtoList(List<ToDo> toDoList) {
        return toDoList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
