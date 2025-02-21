package com.example.projectdemex.model;

import lombok.Getter;

@Getter
public enum TaskPriority {
    LOW("Низкий"),
    MEDIUM("Средний"),
    HIGH("Высокий");

    private final String name;

    TaskPriority(String name) {
        this.name = name;
    }
}
