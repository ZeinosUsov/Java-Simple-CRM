package com.example.projectdemex.controller;

import com.example.projectdemex.dto.UserDto;
import com.example.projectdemex.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserServiceImpl userServiceImpl;

    @GetMapping("/registration")
    String registrationPage(@ModelAttribute("userDto") UserDto userDto) {
        return "registration";
    }

    @PostMapping("/registration")
    String saveUser(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        if (userServiceImpl.isUsernameAvailable(userDto.getUsername())) {
            System.out.println("Пользователь есть!");
            bindingResult.rejectValue("username","error.username", "Имя пользователя уже занято!");
            return "registration";
        }

        if (userServiceImpl.isEmailAvailable(userDto.getEmail())) {
            System.out.println("Чих пых!");
            bindingResult.rejectValue("email", "error.email","Почта уже занята!");
            return "registration";
        }

        userServiceImpl.save(userDto);
        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    String activate(@PathVariable("code") String code, Model model) {

        boolean isActivated = userServiceImpl.activateUser(code);
        if (isActivated){
            model.addAttribute("message", "Активация прошла успешно!");
        }
        else {
            model.addAttribute("message", "Активация не удалась!");
        }

        return "login";
    }

}
