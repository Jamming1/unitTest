package com.example.unitTest.features.controller;
import com.example.unitTest.features.entities.User;
import com.example.unitTest.features.repository.UserRepository;
import com.example.unitTest.features.service.UserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public @ResponseBody User create(@RequestBody User personalUser) {
        return userRepository.save(personalUser);
    }
    @GetMapping("/")
    public @ResponseBody List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @GetMapping("/{id}")
    public @ResponseBody User getSingleUserById(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }
    @PutMapping("/{id}")
    public @ResponseBody User update(@PathVariable Long id, @RequestBody @NonNull User personalUser) {
        personalUser.setId(id);
        personalUser.setName(personalUser.getName());
        personalUser.setSurname(personalUser.getSurname());
        personalUser.setAge(personalUser.getAge());
        return userRepository.save(personalUser);
    }
    @PutMapping("/{id}/activation")
    public @ResponseBody User setUserActive(@PathVariable Long id, @RequestParam("activated") boolean activated) {
        return userService.setUserActivationStatus(id, activated);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}