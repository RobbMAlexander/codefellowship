package com.robbmalexander.codefellowship.repository;

import com.robbmalexander.codefellowship.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findPostById(Long id);
    Post findPostByPosterUsername(String username);
}
