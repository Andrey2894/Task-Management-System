package com.example.taskmanagementsystem.bll;

import com.example.taskmanagementsystem.bll.mappers.TaskMapper;
import com.example.taskmanagementsystem.bll.mappers.UserMapper;
import com.example.taskmanagementsystem.dal.dao.UserRepository;
import com.example.taskmanagementsystem.dal.entity.Task;
import com.example.taskmanagementsystem.dal.entity.User;
import com.example.taskmanagementsystem.ep.dto.TaskDto;
import com.example.taskmanagementsystem.ep.dto.UserDto;
import com.example.taskmanagementsystem.exception.PasswordIsNullException;
import com.example.taskmanagementsystem.exception.TitleIsNullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
        if(userDto.getPassword() != null) user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        else throw new PasswordIsNullException();
        userRepository.save(user);
    }

    public String login(UserDto userDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword())
        );
        return jwtUtil.generateToken(userDto.getUsername());
    }

}
