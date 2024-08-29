package com.devskiller.tasks.blog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Comment {

	@Id
	@GeneratedValue
	private Long id;

	private Long postId;

	private String content;

	private String author;

	private LocalDateTime creationDate;


	public Long getId() {
		return id;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Comment comment = (Comment) o;
		return Objects.equals(id, comment.id) && Objects.equals(postId, comment.postId) && Objects.equals(content, comment.content) && Objects.equals(author, comment.author) && Objects.equals(creationDate, comment.creationDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, postId, content, author, creationDate);
	}
}
