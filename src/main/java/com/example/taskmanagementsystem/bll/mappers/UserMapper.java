package com.example.taskmanagementsystem.bll.mappers;

import com.example.taskmanagementsystem.dal.entity.User;
import com.example.taskmanagementsystem.ep.dto.UserDto;
import com.example.taskmanagementsystem.dal.exception.UsernameIsNullException;

public class UserMapper {
    public static UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setUsername(user.getUsername());
        return dto;
    }
    public static User toEntity(UserDto userDto) {
        User user = new User();
        if(userDto.getUsername() != null) user.setUsername(userDto.getUsername());
        else throw new UsernameIsNullException();
        return user;
    }


}
