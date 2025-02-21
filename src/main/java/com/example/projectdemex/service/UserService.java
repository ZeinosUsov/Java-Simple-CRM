package com.example.projectdemex.service;

import com.example.projectdemex.dto.UserDto;
import com.example.projectdemex.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {
    void save(UserDto userDto);

    User findByUsername(String username);

    List<User> findAllUser();

    List<User> findByName(String name);

    User findById(Long id);

    void updateUser(User user);

    void deleteUser(Long id);

    void updateProfilePhoto(MultipartFile file, UserDetails user) throws IOException;

    boolean isUsernameAvailable(String username);

    boolean isEmailAvailable(String email);

    boolean activateUser(String code);
}
