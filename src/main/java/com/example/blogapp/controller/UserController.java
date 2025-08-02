package com.example.blogapp.controller;

import com.example.blogapp.model.dto.request.PasswordUpdateRequest;
import com.example.blogapp.model.dto.response.UserResponseDTO;
import com.example.blogapp.mapper.UserMapper;
import com.example.blogapp.service.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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

    @PutMapping("/update-password")
    public ResponseEntity<String> updatePassword(@RequestBody PasswordUpdateRequest request,
                                             @AuthenticationPrincipal UserDetails userDetails) {
    boolean updated = userService.updatePassword(userDetails.getUsername(), request);
    if(request.getOldPassword().isEmpty() || request.getNewPassword().isEmpty()){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password can not be empty");
    }
    else if (updated) {
        return ResponseEntity.ok("Password updated successfully");
    } else {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Old password incorrect");
    }
}

}
