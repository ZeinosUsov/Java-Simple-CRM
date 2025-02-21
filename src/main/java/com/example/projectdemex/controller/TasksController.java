package com.example.projectdemex.controller;

import com.example.projectdemex.dto.TaskDto;
import com.example.projectdemex.service.TaskService;
import com.example.projectdemex.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class TasksController {

    private final TaskService taskService;
    private final UserService userService;

    @GetMapping("/admin/tasks/list")
    String findAllTasks(Model model) {
        model.addAttribute("users", userService.findAllUser());
        model.addAttribute("tasks", taskService.findAllTask());
        model.addAttribute("task", new TaskDto());
        return "tasks_list";
    }


    // Не открывается страница с задачами после добавления этого метода
    @PostMapping("/admin/tasks/addTask")
    String addNewTask(@ModelAttribute TaskDto taskDto){
        taskService.save(taskDto);
        return "redirect:/admin/tasks/list";
    }

}
