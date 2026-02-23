package com.agata.login.controller;

import com.agata.login.model.User;
import com.agata.login.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/signup")
public class SignUp {

    private final UserRepository userRepository;
    public SignUp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @PostMapping
    public User Save(@RequestBody @Valid User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "E-mail already exists");
        }
        return userRepository.save(user);
    }

    @GetMapping
    public List<User> findAll() {
        return userRepository.findAll();
    }

}
