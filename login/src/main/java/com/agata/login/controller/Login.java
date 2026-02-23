package com.agata.login.controller;

import com.agata.login.dto.LoginRequest;
import com.agata.login.model.User;
import com.agata.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/login")
public class Login {

    private final UserRepository userRepository;
    @Autowired
    public Login(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public User login(@RequestBody LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        if (!user.getPassword().equals(request.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong password");
        }
        return user;
    }
}
