package com.example.projectdemex.repo;

import com.example.projectdemex.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query(value = "select u from User u where lower(u.name) like concat('%', lower(:search), '%') " +
            "or lower(u.surname) like concat('%', lower(:search), '%') ")
    List<User> findByName(@Param("search") String search);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    User findByActivationCode(String code);
}
