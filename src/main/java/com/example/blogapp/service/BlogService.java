package com.example.blogapp.service;

import com.example.blogapp.exception.ResourceNotFoundException;
import com.example.blogapp.model.entity.Blog;
import com.example.blogapp.repository.BlogRepository;
import com.example.blogapp.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;

    public Blog createBlog(Blog blog, Long id){
        //blog.setUser(currentUser);
        return blogRepository.save(blog);
    }

    public List<Blog> getAllBlogs(){
        return blogRepository.findAll();
    }

    public List<Blog> getBlogsByUserId(Long userId) {
        return blogRepository.findByuserId(userId);
    }

    public void deleteBlog(Long blogId, CustomUserDetails currentUser) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new ResourceNotFoundException("Blog not found"));

        boolean isOwner = blog.getUser().getId().equals(currentUser.getId());
        boolean isAdmin = currentUser.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!isOwner && !isAdmin) {
            throw new AccessDeniedException("You do not have permission to delete this blog.");
        }

        blogRepository.deleteById(blogId);
    }

}
