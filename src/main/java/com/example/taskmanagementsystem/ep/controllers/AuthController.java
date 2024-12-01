package com.example.taskmanagementsystem.ep.controllers;

import com.example.taskmanagementsystem.bll.UserService;
import com.example.taskmanagementsystem.ep.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody UserDto userDto) {
        userService.createUser(userDto);
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDto userDto) {
        return userService.login(userDto);
    }
}
