package com.example.blogapp.repository;

import com.example.blogapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByuserName(String userName);
    User findByUserName(String userName);

}
