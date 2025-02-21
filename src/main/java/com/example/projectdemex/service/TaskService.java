package com.example.projectdemex.service;

import com.example.projectdemex.dto.TaskDto;
import com.example.projectdemex.model.Tasks;

import java.util.List;

public interface TaskService {

    List<Tasks> findAllTask();

    void save(TaskDto taskDto);
}
