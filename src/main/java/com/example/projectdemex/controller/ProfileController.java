package com.example.projectdemex.controller;

import com.example.projectdemex.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;

    @GetMapping("/profile")
    String profile(@AuthenticationPrincipal UserDetails user, Model model) {
        model.addAttribute("user", userService.findByUsername(user.getUsername()));
        return "profile";
    }

    @PostMapping("/updatePhoto")
    String updatePhotoProfile(@AuthenticationPrincipal UserDetails user,
                              @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        userService.updateProfilePhoto(file, user);
        return "redirect:/profile";
    }

}
