package com.example.blogapp.service;

import com.example.blogapp.model.entity.User;
import com.example.blogapp.repository.BlogRepository;
import com.example.blogapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BlogRepository blogRepository;

    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

     @Transactional
    public void deleteUserAndBlogs(Long targetUserId) {

        blogRepository.deleteAllByUserId(targetUserId);
        userRepository.deleteById(targetUserId);
    }
}
