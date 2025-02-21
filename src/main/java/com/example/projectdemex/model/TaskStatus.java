package com.example.projectdemex.model;

import lombok.Getter;

@Getter
public enum TaskStatus {
    OPEN("Открыта"),
    IN_PROGRESS("В процессе"),
    CLOSED("Закрыта");

    private final String name;

    TaskStatus(String name) {
        this.name = name;
    }
}
