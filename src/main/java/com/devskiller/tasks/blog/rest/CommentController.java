package com.devskiller.tasks.blog.rest;

import com.devskiller.tasks.blog.model.dto.CommentDto;
import com.devskiller.tasks.blog.model.dto.NewCommentDto;
import com.devskiller.tasks.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/posts")
public class CommentController {

	private final CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	@PostMapping(value = "/{postId}/comments")
	public ResponseEntity<Long> createComment(@PathVariable Long postId, @RequestBody NewCommentDto dto) {

		try {
			Long aLong = commentService.addComment(postId, dto);
			return new ResponseEntity<>(aLong, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


	@GetMapping(value = "/{postId}/comments")
	public ResponseEntity<List<CommentDto>> getCommentsForPost(@PathVariable Long postId) {

		List<CommentDto> commentDtos = commentService.getCommentsForPost(postId);

		return new ResponseEntity<>(commentDtos, HttpStatus.OK);

	}
}
