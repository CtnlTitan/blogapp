package com.example.blogapp.exception;
public class UsernameException extends RuntimeException {
    public UsernameException(String username) {
        super("Username '" + username + "' is already taken.");
    }
    public UsernameException(){
        super("Username cannot be empty");
    }
}
