package com.example.cryptoTrading.service;

import com.example.cryptoTrading.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> getUserById(Long id);
    User getUserByEmail(String email);
    User createUser(String email, String password);
}
