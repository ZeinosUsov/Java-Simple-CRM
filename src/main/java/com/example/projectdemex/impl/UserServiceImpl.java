package com.example.projectdemex.impl;

import com.example.projectdemex.dto.UserDto;
import com.example.projectdemex.model.Role;
import com.example.projectdemex.model.User;
import com.example.projectdemex.repo.UserRepo;
import com.example.projectdemex.service.MailSender;
import com.example.projectdemex.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final MailSender mailSender;

    @Value("${upload.path}")
    private String uploadPath;


    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public List<User> findAllUser() {
        return userRepo.findAll();
    }

    @Override
    public void save(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        userRepo.save(user);

        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format("Здравствуйте, %s!\n" +
                            "Для активации вашего аккаунта просим перейти вас по ссылке: http://localhost:8080/activate/%s",
                    user.getName(), user.getActivationCode());

            mailSender.send(user.getEmail(), "Активация аккаунта", message);
        }
    }

    @Override
    public List<User> findByName(String name) {
        return userRepo.findByName(name);
    }

    @Override
    public User findById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Пользователь с ID: " + id + " не найден!"));
    }

    @Override
    public void updateUser(User user) {
        User updateUser = userRepo.findById(user.getId()).orElse(null);
        if (updateUser == null) {
            throw new IllegalArgumentException("Пользователь не найден!");
        }
        updateUser.setName(user.getName());
        updateUser.setSurname(user.getSurname());
        updateUser.setUsername(user.getUsername());
        updateUser.setEmail(user.getEmail());
        updateUser.setPhone(user.getPhone());
        userRepo.save(updateUser);
    }

    @Override
    public void deleteUser(Long id) {
        this.userRepo.deleteById(id);
    }

    @Override
    public void updateProfilePhoto(MultipartFile file, UserDetails userDetails) throws IOException {
        if (file != null && !file.isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                boolean isSuccessful = uploadDir.mkdir();
                if (!isSuccessful) {
                    throw new IOException("Ошибка при создании директории");
                }
            }
            String originalFileName = file.getOriginalFilename();
            if (originalFileName != null) {
                String uuidFile = UUID.randomUUID().toString();
                String newFileName = uuidFile + "." + originalFileName;
                file.transferTo(new File(uploadPath + "/" + newFileName));
                User user = userRepo.findByUsername(userDetails.getUsername());
                user.setFilename(newFileName);
                userRepo.save(user);
            }
        }
    }

    @Override
    public boolean isUsernameAvailable(String username) {
        return userRepo.existsByUsername(username);
    }

    @Override
    public boolean isEmailAvailable(String email) {
        return userRepo.existsByEmail(email);
    }

    @Override
    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);
        if (user == null){
            return false;
        }
        user.setActivationCode(null);
        userRepo.save(user);
        return true;
    }


}
