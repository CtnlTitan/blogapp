package com.example.blogapp.mapper;

import com.example.blogapp.model.dto.request.UserRequestDTO;
import com.example.blogapp.model.dto.response.UserResponseDTO;
import com.example.blogapp.model.entity.User;

public class UserMapper {

    private UserMapper() {
        throw new UnsupportedOperationException("Utility class");
    }
    public static UserResponseDTO toDto(User user) {
        if (user==null)
            return null;
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setUserName(user.getUserName());
        dto.setEmail(user.getEmail());
        return dto;
    }

    public static User toEntity(UserRequestDTO dto) {
        if (dto==null)
            return null;
        User user = new User();
        user.setUserName(dto.getUserName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }
}

