package com.devskiller.tasks.blog.service;

import com.devskiller.tasks.blog.model.Comment;
import com.devskiller.tasks.blog.model.Post;
import com.devskiller.tasks.blog.model.dto.CommentDto;
import com.devskiller.tasks.blog.model.dto.NewCommentDto;
import com.devskiller.tasks.blog.repository.CommentRepository;
import com.devskiller.tasks.blog.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class CommentService {

	private final CommentRepository commentRepository;
	private final PostRepository postRepository;

	public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
		this.commentRepository = commentRepository;
		this.postRepository = postRepository;
	}

	/**
	 * Returns a list of all comments for a blog post with passed id.
	 *
	 * @param postId id of the post
	 * @return list of comments sorted by creation date descending - most recent first
	 */
	public List<CommentDto> getCommentsForPost(Long postId) {

		return commentRepository
			.findAllByPostId(postId)
			.stream()
			.sorted(Comparator.comparing(Comment::getCreationDate).reversed())
			.map(comment -> new CommentDto(comment.getId(), comment.getContent(), comment.getAuthor(), comment.getCreationDate()))
			.toList();

	}

	/**
	 * Creates a new comment
	 *
	 * @param postId        id of the post
	 * @param newCommentDto data of new comment
	 * @return id of the created comment
	 * @throws IllegalArgumentException if postId is null or there is no blog post for passed postId
	 */
	public Long addComment(Long postId, NewCommentDto newCommentDto) {

		if (!Objects.nonNull(postId)) {
			throw new IllegalArgumentException("Post id cannot be null");
		}
		Post post = postRepository
			.findById(postId)
			.orElseThrow(() -> new IllegalArgumentException("Post not found"));

		Comment comment = new Comment();
		comment.setPostId(post.getId());
		comment.setContent(newCommentDto.content());
		comment.setAuthor(newCommentDto.author());
		comment.setCreationDate(LocalDateTime.now());

		commentRepository.save(comment);

		return comment.getId();

	}
}
