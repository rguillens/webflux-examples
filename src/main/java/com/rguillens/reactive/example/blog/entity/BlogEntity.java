package com.rguillens.reactive.example.blog.entity;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Table("blogs")
public class BlogEntity extends BaseEntity {
    @Column("name")
    private final String name;

    @PersistenceConstructor
    public BlogEntity(Long id, String name) {
        super(id);
        this.name = name;
    }

    public BlogEntity(BlogDto dto) {
        this(dto.getId(), dto.getName());
    }

    @Override
    public BlogEntity withId(Long id) {
        return new BlogEntity(id,
                this.name);
    }

    public BlogEntity withName(String name) {
        return new BlogEntity(this.id,
                name);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlogEntity)) return false;

        BlogEntity that = (BlogEntity) o;

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public BlogDto toDto(List<BlogPostDto> posts) {
        return new BlogDto(id, name, posts);
    }
}
