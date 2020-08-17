package com.rguillens.reactive.example.blog.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;

public abstract class BaseEntity {
    protected static final int HASH = 31;

    @Id
    @Column("id")
    protected final Long id;

    @CreatedDate
    @Column("created_at")
    protected LocalDateTime createdAt;

    @LastModifiedDate
    @Column("updated_at")
    protected LocalDateTime updatedAt;

    protected BaseEntity(Long id) {
        this.id = id;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public abstract <T extends BaseEntity> T withId(Long id);

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
