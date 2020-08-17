package com.rguillens.reactive.example.blog.entity;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Table("posts")
public class PostEntity extends BaseEntity {

    @Column("blog_id")
    private final Long blogId;

    @NotBlank
    @Column("author")
    private final String author;

    @NotBlank
    @Column("title")
    private final String title;

    @NotBlank
    @Column("summary")
    private final String summary;

    @NotBlank
    @Column("content")
    private final String content;

    @NotNull
    @Column("published")
    private final Boolean published;

    @Column("published_at")
    private final LocalDateTime publishedAt;

    @PersistenceConstructor
    public PostEntity(Long id,
                      Long blogId,
                      String author,
                      String title,
                      String summary,
                      String content,
                      Boolean published,
                      LocalDateTime publishedAt) {
        super(id);
        this.blogId = blogId;
        this.author = author;
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.published = published;
        this.publishedAt = publishedAt;
    }

    @Override
    public PostEntity withId(Long id) {
        return new PostEntity(id,
                this.blogId,
                this.author,
                this.title,
                this.summary,
                this.content,
                this.published,
                this.publishedAt);
    }

    public PostEntity withBlogId(Long blogId) {
        return new PostEntity(id,
                blogId,
                this.author,
                this.title,
                this.summary,
                this.content,
                this.published,
                this.publishedAt);
    }

    public PostEntity withAuthor(String author) {
        return new PostEntity(this.id,
                this.blogId,
                author,
                this.title,
                this.summary,
                this.content,
                this.published,
                this.publishedAt);
    }

    public PostEntity withTitleAndSummaryAndContent(String title,
                                                    String summary,
                                                    String content) {
        return new PostEntity(this.id,
                this.blogId,
                this.author,
                title,
                summary,
                content,
                this.published,
                this.publishedAt);
    }

    public PostEntity withPublished(Boolean published) {
        return new PostEntity(this.id,
                this.blogId,
                this.author,
                this.title,
                this.summary,
                this.content,
                published,
                published ? LocalDateTime.now() : null);
    }

    public Long getBlogId() {
        return blogId;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getContent() {
        return content;
    }

    public Boolean getPublished() {
        return published;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostEntity)) return false;

        PostEntity that = (PostEntity) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return HASH;
    }

    public BlogPostDto toDto() {
        return new BlogPostDto(id,
                title,
                summary);
    }
}
