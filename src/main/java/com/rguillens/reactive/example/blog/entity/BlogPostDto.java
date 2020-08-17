package com.rguillens.reactive.example.blog.entity;

public class BlogPostDto {

    private final Long id;

    private final String title;

    private final String summary;

    public BlogPostDto(Long id, String title, String summary) {
        this.id = id;
        this.title = title;
        this.summary = summary;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }
}
