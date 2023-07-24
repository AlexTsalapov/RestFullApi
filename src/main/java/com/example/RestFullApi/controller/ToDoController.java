package com.example.RestFullApi.controller;

import com.example.RestFullApi.dto.ToDoDTO;
import com.example.RestFullApi.facade.ToDoFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class ToDoController {
    private final ToDoFacade toDoFacade;

    public ToDoController(ToDoFacade toDoFacade) {
        this.toDoFacade = toDoFacade;
    }

    @GetMapping("/getAllToDoUser")
    public ResponseEntity<?> getToDosById(Authentication authentication) {
        try {
            String username = authentication.getName();
            List<ToDoDTO> todo = toDoFacade.getToDosByUser(username);
            return ResponseEntity.ok(todo);
        }
        catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");

        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createToDo(@RequestBody ToDoDTO toDoDTO, Authentication authentication) {
        try {
            String username = authentication.getName();
            ToDoDTO createdToDo = toDoFacade.createToDo(toDoDTO, username);
            return ResponseEntity.ok(createdToDo);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Failed to add todo");
        }

    }

    @PutMapping("/update")
    public ResponseEntity<?> updateToDo( @RequestBody ToDoDTO toDoDTO) throws Exception {
        try {
            ToDoDTO updatedToDo = toDoFacade.updateToDo( toDoDTO);
            return ResponseEntity.ok(updatedToDo);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Todo not found");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteToDoById(@PathVariable Long id) throws Exception {
        try {
            toDoFacade.deleteToDoById(id);
            return ResponseEntity.ok().body("todo is deleted");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Todo not found");
        }


    }
}
