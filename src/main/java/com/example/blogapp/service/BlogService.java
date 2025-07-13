package com.example.blogapp.service;

import com.example.blogapp.exception.ResourceNotFoundException;
import com.example.blogapp.model.entity.Blog;
import com.example.blogapp.model.entity.User;
import com.example.blogapp.repository.BlogRepository;
import com.example.blogapp.repository.UserRepository;
import com.example.blogapp.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;

import jakarta.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    public Blog createBlog(Blog blog){
        return blogRepository.save(blog);
    }

    public List<Blog> getAllBlogs(){
        return blogRepository.findAll();
    }

    public List<Blog> getBlogsByUserName(String userName) {
        long id=userRepository.findByUserName(userName).getId();
        return blogRepository.findByUserId(id);
    }

    public void deleteBlog(Long blogId, User user) {
         Blog blog = blogRepository.findById(blogId)
        .orElseThrow(() -> new ResourceNotFoundException("Blog", "id",blogId));

    
        boolean isAdmin = user.getRole().name().equals("ADMIN");
        boolean isOwner = blog.getUser().getId().equals(user.getId());

        if (!isAdmin && !isOwner) {
            throw new AccessDeniedException("You are not allowed to delete this blog");
        }
            blogRepository.deleteById(blogId);
        }

    public List<Blog> searchBlogs(String name, List<String> tags, String category) {
    Specification<Blog> spec = (root, query, cb) -> {
        List<Predicate> predicates = new ArrayList<>();

        if (name != null && !name.isBlank()) {
            predicates.add(cb.like(cb.lower(root.get("blogName")), "%" + name.toLowerCase() + "%"));
        }

        if (category != null && !category.isBlank()) {
            predicates.add(cb.equal(cb.lower(root.get("category")), category.toLowerCase()));
        }

        if (tags != null && !tags.isEmpty()) {
            predicates.add(root.join("tags").in(tags));
        }

        return cb.and(predicates.toArray(new Predicate[0]));
    };

    return blogRepository.findAll(spec);
}

}
