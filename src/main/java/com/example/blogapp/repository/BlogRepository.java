package com.example.blogapp.repository;

import com.example.blogapp.model.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog,Long> {

    List<Blog> findByuserId(Long userId);
    List<Blog> findBycategory(String category);
    @Query("SELECT b FROM Blog b JOIN b.tags t WHERE t IN :tags")
    List<Blog> findBytags(@Param("tags") List<String> tags);
}
