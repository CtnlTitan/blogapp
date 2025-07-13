package com.example.blogapp.service;

import com.example.blogapp.exception.UsernameException;
import com.example.blogapp.model.dto.request.AuthRequestDTO;
import com.example.blogapp.model.dto.response.AuthResponseDTO;
import com.example.blogapp.model.entity.User;
import com.example.blogapp.model.entity.Role;
import com.example.blogapp.repository.UserRepository;
import com.example.blogapp.security.JwtUtil;
import com.example.blogapp.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResponseDTO login(AuthRequestDTO request) {
        // Authenticate user credentials
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUserName(),
                        request.getPassword()
                )
        );

        // Cast authenticated principal to CustomUserDetails
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        // Generate token with role
        String token = jwtUtil.generateToken(userDetails);
        return new AuthResponseDTO(token);
    }

    public AuthResponseDTO register(AuthRequestDTO request){
        if (userRepository.existsByuserName(request.getUserName())) {
            throw new UsernameException(request.getUserName());
        }
       else if (request.getUserName() == null || request.getUserName().trim().isEmpty()) {
            throw new UsernameException();
        }
        if (request.getEmail() == null || !request.getEmail().contains("@")) {
            throw new IllegalArgumentException("Invalid email address.");
        }

        // Create and save user
        User user = new User();
        user.setUserName(request.getUserName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail()); // optional: include in DTO
        user.setRole(Role.USER); // default role

        userRepository.save(user);

        // Create UserDetails manually
        CustomUserDetails userDetails = new CustomUserDetails(user);

        // Generate token with role
        String token = jwtUtil.generateToken(userDetails);
        return new AuthResponseDTO(token);
    }
}
