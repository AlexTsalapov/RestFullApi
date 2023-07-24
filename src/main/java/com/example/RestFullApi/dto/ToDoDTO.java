package com.example.RestFullApi.dto;
import lombok.Data;
import java.time.LocalDateTime;
@Data
public class ToDoDTO {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private boolean completed;

}
