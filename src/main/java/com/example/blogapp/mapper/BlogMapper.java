package com.example.blogapp.mapper;

import com.example.blogapp.model.dto.request.BlogRequestDTO;
import com.example.blogapp.model.dto.response.BlogResponseDTO;
import com.example.blogapp.model.entity.Blog;
import com.example.blogapp.model.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class BlogMapper {

    private BlogMapper(){
        throw new UnsupportedOperationException("Utility class");
    }

    public static Blog toEntity(BlogRequestDTO dto, User user){
        if(dto==null || user==null)
            return null;
        Blog blog=new Blog();
        blog.setBlogName(dto.getBlogName());
        blog.setCategory(dto.getCategory());
        blog.setUser(user);
        blog.setTags(dto.getTags());
        blog.setContent(dto.getContent());
        return blog;
    }

    public static BlogResponseDTO toDto(Blog blog) {
        if(blog==null)
            return null;
        BlogResponseDTO dto = new BlogResponseDTO();
        dto.setId(blog.getId());
        dto.setBlogName(blog.getBlogName());
        dto.setCategory(blog.getCategory());
        dto.setTags(blog.getTags());
        dto.setUserName(blog.getUser().getUserName());
        dto.setContent(blog.getContent());
        return dto;
    }
    public static List<BlogResponseDTO> toDtoList(List<Blog> blogs) {
        if (blogs == null) return List.of(); // or Collections.emptyList()
        return blogs.stream()
                .map(BlogMapper::toDto)
                .collect(Collectors.toList());
    }
}
