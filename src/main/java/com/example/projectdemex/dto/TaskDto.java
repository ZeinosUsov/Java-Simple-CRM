package com.example.projectdemex.dto;

import com.example.projectdemex.model.TaskPriority;
import com.example.projectdemex.model.TaskStatus;
import com.example.projectdemex.model.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TaskDto {
    private String title;
    private String description;
    private LocalDateTime deadline;
    private TaskStatus status;
    private TaskPriority priority;
    private User user;
}
