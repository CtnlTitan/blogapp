package com.example.blogapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/about")
public class AboutController {
   @GetMapping
    public ResponseEntity<Map<String, String>> getAboutInfo() {
        return ResponseEntity.ok(Map.of(
                "project", "Blog Platform",
                "version", "1.0.0",
                "description", "A simple blogging platform built with Spring Boot and MySQL.",
                "author", "Devansh Kushwaha"
        ));
    }
    
}
