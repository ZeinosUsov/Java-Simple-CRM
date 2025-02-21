package com.example.projectdemex.controller;

import com.example.projectdemex.impl.UserServiceImpl;
import com.example.projectdemex.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/admin/user")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @GetMapping("/list")
    String userList(@RequestParam(value = "search", required = false) String search,
                    Model model) {
        model.addAttribute("user_list", search == null || search.isEmpty()
                ? userService.findAllUser()
                : userService.findByName(search));
        return "user_list";
    }

    @GetMapping("/update")
    String updateUser(@RequestParam(value = "id",required = false) Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "user_update";
    }

    @PostMapping("/update")
    String saveUpdate(@ModelAttribute("user") User user){
        userService.updateUser(user);
        return "redirect:/admin/user/list";
    }

    @GetMapping("/delete")
    String deleteUser(@RequestParam(value = "id",required = false) Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/user/list";
    }
}
