package com.yjlearning.ProjectManagement.service;

import java.util.Optional;

import com.yjlearning.ProjectManagement.entity.User;

public interface UserService {
    User save(User user);

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
}
