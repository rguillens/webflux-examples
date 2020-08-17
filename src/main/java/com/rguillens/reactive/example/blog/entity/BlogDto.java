package com.rguillens.reactive.example.blog.entity;

import javax.validation.constraints.NotBlank;
import java.util.Collections;
import java.util.List;

public class BlogDto {
    private final Long id;
    @NotBlank
    private final String name;
    private final List<BlogPostDto> posts;

    public BlogDto(Long id, String name, List<BlogPostDto> posts) {
        this.id = id;
        this.name = name;
        this.posts = posts != null ? Collections.unmodifiableList(posts) : Collections.emptyList();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<BlogPostDto> getPosts() {
        return posts;
    }
}
