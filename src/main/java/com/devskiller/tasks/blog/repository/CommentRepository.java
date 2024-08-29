package com.devskiller.tasks.blog.repository;

import com.devskiller.tasks.blog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	Comment findById(long id);

	List<Comment> findAllByPostId(long postId);

}
