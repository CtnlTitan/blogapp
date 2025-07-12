package com.example.blogapp.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequestDTO {
    private String userName;
    private String email;
    private String password;
}
