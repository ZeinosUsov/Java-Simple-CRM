package com.example.projectdemex.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    @NotEmpty(message = "Имя пользователя не может быть пустым!")
    @Pattern(regexp = "^[А-ЯЁ][а-я]{0,15}$|^[A-Z][a-z]{0,15}$", message = "Имя должно начинаться с заглавной буквы и содержать только буквы кириллицы или латиницы")
    @Size(min = 2, max = 20, message = "Имя должно содержать от 2 до 20 символов")
    private String name;
    @NotEmpty(message = "Фамилия пользователя не может быть пустой!")
    @Pattern(regexp = "^[А-ЯЁ][а-я]{0,15}$|^[A-Z][a-z]{0,15}$", message = "Фамилия должна начинаться с заглавной буквы и содержать только буквы кириллицы или латиницы")
    @Size(min = 2, max = 20, message = "Фамилия должна содержать от 2 до 20 символов")
    private String surname;
    private String phone;
    @NotEmpty(message = "Почта не может быть пустой!")
    @Pattern(regexp = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$", message = "Некорректный адрес электронной почты")
    private String email;
    @NotEmpty(message = "Логин пользователя не может быть пустым!")
    @Pattern(regexp = "^[A-Za-zА-Яа-я0-9_-][A-Za-zА-Яа-я0-9_-]{6,18}$", message = "Логин пользователя может содержать только буквы кириллицы или латиницы, а так же цифры от 0 до 9 и знаки \"-\" и \"_\"")
    @Size(min = 6, max = 18, message = "Логин должен содержать от 6 до 18 символов")
    private String username;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+]).{8,20}$",
            message = "Пароль должен содержать от 8 до 20 символов, включая хотя бы одну букву в верхнем регистре, одну букву в нижнем регистре, одну цифру и один специальный символ (!@#$%^&*()_+)")
    @Size(min = 8, max = 20, message = "Пароль должен быть не короче 8 символов и не длиннее 20")
    private String password;
    private String filename;
}
