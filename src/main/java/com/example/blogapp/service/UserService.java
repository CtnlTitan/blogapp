package com.example.blogapp.service;

import com.example.blogapp.model.dto.request.PasswordUpdateRequest;
import com.example.blogapp.model.entity.User;
import com.example.blogapp.repository.BlogRepository;
import com.example.blogapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BlogRepository blogRepository;
    private final PasswordEncoder passwordEncoder;

    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

     @Transactional
    public void deleteUserAndBlogs(Long targetUserId) {

        blogRepository.deleteAllByUserId(targetUserId);
        userRepository.deleteById(targetUserId);
    }

     public boolean updatePassword(String username, PasswordUpdateRequest request) {
        User user = userRepository.findByUserName(username);
        if(user==null)
        {
            throw new UsernameNotFoundException("User not found");
        }
        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            return false; // Old password doesn't match
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
        return true;
    }
}
