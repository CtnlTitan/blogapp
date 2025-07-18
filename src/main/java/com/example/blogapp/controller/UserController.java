package com.example.blogapp.controller;

import com.example.blogapp.model.dto.response.UserResponseDTO;
import com.example.blogapp.mapper.UserMapper;
import com.example.blogapp.service.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@SecurityRequirement(name = "JWT")
public class UserController {
    private final UserService userService;

    @GetMapping("/{username}")
    @PreAuthorize("#username == authentication.principal.username or hasRole('ADMIN')")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable String username) {
        UserResponseDTO user = UserMapper.toDto(userService.findByUserName(username));
        return ResponseEntity.status(200).body(user);

    }

    @PreAuthorize("hasRole('ADMIN') or #userId == principal.id")
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
    userService.deleteUserAndBlogs(userId);
    return ResponseEntity.ok("User and all associated blogs deleted successfully.");
    }
}
