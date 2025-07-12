package com.example.blogapp.controller;

import com.example.blogapp.mapper.BlogMapper;
import com.example.blogapp.model.dto.request.BlogRequestDTO;
import com.example.blogapp.model.dto.response.BlogResponseDTO;
import com.example.blogapp.security.CustomUserDetails;
import com.example.blogapp.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/blogs")
public class BlogController {

    private final BlogService blogService;

    // Create a blog
    @PostMapping("/create")
    public ResponseEntity<BlogResponseDTO> createBlog(@RequestBody BlogRequestDTO blogRequest,
                                                      @AuthenticationPrincipal CustomUserDetails currentUser) {
        BlogResponseDTO created = BlogMapper.toDto(
                blogService.createBlog(BlogMapper.toEntity(blogRequest,currentUser.getUser()), currentUser.getId())
        );
        return ResponseEntity.status(201).body(created);
    }

    // Get all blogs
    @GetMapping
    public ResponseEntity<List<BlogResponseDTO>> getAllBlogs() {
        List<BlogResponseDTO> blogs = BlogMapper.toDtoList(blogService.getAllBlogs());
        return ResponseEntity.ok(blogs);
    }

    // Get blogs by user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BlogResponseDTO>> getBlogsByUser(@PathVariable Long userId) {
        List<BlogResponseDTO> blogs = BlogMapper.toDtoList(blogService.getBlogsByUserId(userId));
        return ResponseEntity.ok(blogs);
    }
}
