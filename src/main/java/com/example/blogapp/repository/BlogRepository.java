package com.example.blogapp.repository;

import com.example.blogapp.model.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.List;

public interface BlogRepository extends JpaRepository<Blog,Long> , JpaSpecificationExecutor<Blog> {

    List<Blog> findByUserId(long id);

    void deleteAllByUserId(Long targetUserId);
}
