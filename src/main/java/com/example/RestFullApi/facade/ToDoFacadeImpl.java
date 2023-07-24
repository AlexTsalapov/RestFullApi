package com.example.RestFullApi.facade;

import com.example.RestFullApi.dto.ToDoDTO;
import com.example.RestFullApi.entity.ToDo;
import com.example.RestFullApi.mapper.ToDoMapper;
import com.example.RestFullApi.service.JwtUserDetailsService;
import com.example.RestFullApi.service.ToDoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoFacadeImpl implements ToDoFacade {
    private final ToDoService toDoService;
    private final ToDoMapper toDoMapper;
    private final JwtUserDetailsService userService;

    public ToDoFacadeImpl(ToDoService toDoService, ToDoMapper toDoMapper, JwtUserDetailsService userService) {
        this.toDoService = toDoService;
        this.toDoMapper = toDoMapper;
        this.userService = userService;
    }

    @Override
    public List<ToDoDTO> getToDosByUser(String username) {
        List<ToDo> toDoList = toDoService.getToDosByUser(userService.loadUserByUsername(username));
        return toDoMapper.toDtoList(toDoList);
    }

    @Override
    public ToDoDTO createToDo(ToDoDTO toDoDTO, String  username) {
        ToDo toDo = toDoMapper.toEntity(toDoDTO,userService.loadUserByUsername(username));
        ToDo createdToDo = toDoService.createToDo(toDo);
        return toDoMapper.toDto(createdToDo);
    }

    @Override
    public ToDoDTO updateToDo( ToDoDTO toDoDTO) throws Exception {
        ToDo existingToDo = toDoService.getToDoById(toDoDTO.getId());
        if (existingToDo == null) {
            throw new Exception("ToDo not found");
        }
        ToDo toDo = toDoMapper.updateEntity(toDoDTO, existingToDo);
        ToDo updatedToDo = toDoService.updateToDo(toDo);
        return toDoMapper.toDto(updatedToDo);

    }

    @Override
    public void deleteToDoById(Long id) throws Exception {
        try {
            toDoService.deleteToDoById(id);
        }
        catch (Exception ex){
            throw new Exception("Todo not found");
        }

    }
}
