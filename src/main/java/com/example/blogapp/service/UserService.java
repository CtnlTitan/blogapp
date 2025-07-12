package com.example.blogapp.service;

import com.example.blogapp.model.entity.User;
import com.example.blogapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findByuserName(String userName){
        return userRepository.findByuserName(userName);
    }

}
