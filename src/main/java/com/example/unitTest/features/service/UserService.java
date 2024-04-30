package com.example.unitTest.features.service;

import com.example.unitTest.features.entities.User;
import com.example.unitTest.features.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User setUserActivationStatus(Long userId, boolean isActive) {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) return null;
        user.get().setActive(isActive);
        return userRepository.save(user.get());
    }
}