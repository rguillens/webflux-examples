package com.rguillens.reactive.example.blog.entity;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Table("comments")
public class CommentEntity extends BaseEntity {
    @NotBlank
    @Column("author")
    private final String author;

    @NotBlank
    @Column("content")
    private final String content;

    @Column("post_id")
    private final Long postId;

    @PersistenceConstructor
    public CommentEntity(Long id, String author, String content, Long postId) {
        super(id);
        this.author = author;
        this.content = content;
        this.postId = postId;
    }

    @Override
    public CommentEntity withId(Long id) {
        return new CommentEntity(id,
                this.author,
                this.content,
                this.postId);
    }

    public CommentEntity withPostId(Long postId) {
        return new CommentEntity(id,
                this.author,
                this.content,
                postId);
    }

    public CommentEntity withContent(String content) {
        return new CommentEntity(this.id,
                this.author,
                content,
                this.postId);
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public Long getPostId() {
        return postId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentEntity)) return false;

        CommentEntity that = (CommentEntity) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return HASH;
    }
}
