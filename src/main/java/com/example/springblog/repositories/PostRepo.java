package com.example.springblog.repositories;

import com.example.springblog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {
    List<Post> findByTitleContainingOrBodyContaining(String title, String body);
}

