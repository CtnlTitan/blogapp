package com.example.blogapp.controller;

import com.example.blogapp.mapper.BlogMapper;
import com.example.blogapp.model.dto.request.BlogRequestDTO;
import com.example.blogapp.model.dto.response.BlogResponseDTO;
import com.example.blogapp.security.CustomUserDetails;
import com.example.blogapp.service.BlogService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/blogs")
@SecurityRequirement(name = "JWT")
public class BlogController {

    private final BlogService blogService;

    // Create a blog
    @PostMapping("/create")
    public ResponseEntity<BlogResponseDTO> createBlog(@RequestBody BlogRequestDTO blogRequest,
                                                      @AuthenticationPrincipal CustomUserDetails currentUser) {
        BlogResponseDTO created = BlogMapper.toDto(
                blogService.createBlog(BlogMapper.toEntity(blogRequest,currentUser.getUser()))
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
    @GetMapping("/user/{userName}")
    public ResponseEntity<List<BlogResponseDTO>> getBlogsByUserName(@PathVariable String userName) {
        List<BlogResponseDTO> blogs = BlogMapper.toDtoList(blogService.getBlogsByUserName(userName));
        return ResponseEntity.ok(blogs);
    }

    @PostMapping("/search")
    public ResponseEntity<List<BlogResponseDTO>> searchBlogs(@RequestBody BlogRequestDTO request) {
        List<BlogResponseDTO> results = BlogMapper.toDtoList(
            blogService.searchBlogs(request.getBlogName(), request.getTags(), request.getCategory())
        );
        return ResponseEntity.ok(results);
    }

    @DeleteMapping("/{blogId}")
    public ResponseEntity<String> deleteBlog(@PathVariable Long blogId,@AuthenticationPrincipal CustomUserDetails user){
        blogService.deleteBlog(blogId,user.getUser());
        return ResponseEntity.ok("Blog Deleted");
    }
}
