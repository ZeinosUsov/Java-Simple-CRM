package com.example.projectdemex.repo;

import com.example.projectdemex.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Tasks, Long> {
}
