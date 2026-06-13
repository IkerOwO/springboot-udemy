package com.iker.springboot_error.services;

import com.iker.springboot_error.models.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> findById(Long id);
}
