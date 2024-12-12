package com.example.taskmanagementsystem.service;

import com.example.taskmanagementsystem.mapper.UserMapper;
import com.example.taskmanagementsystem.security.JwtUtil;
import com.example.taskmanagementsystem.repository.UserRepository;
import com.example.taskmanagementsystem.entity.User;
import com.example.taskmanagementsystem.exception.PasswordIsNullException;
import com.example.taskmanagementsystem.exception.UsernameAlreadyExistsException;
import com.example.taskmanagementsystem.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    public void createUser(UserDto userDto) {
        User user = UserMapper.toEntity(userDto);
        if(userRepository.findByUsername(userDto.getUsername()).isPresent()) throw new UsernameAlreadyExistsException();
        if(userDto.getPassword().isEmpty()) throw new PasswordIsNullException();
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
    }

    public String login(UserDto userDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword())
        );
        return jwtUtil.generateToken(userDto.getUsername());
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
