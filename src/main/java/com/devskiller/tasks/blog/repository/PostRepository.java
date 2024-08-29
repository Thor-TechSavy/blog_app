package com.devskiller.tasks.blog.repository;

import com.devskiller.tasks.blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
