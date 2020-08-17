package com.rguillens.reactive.example.blog.entity.dao;

import com.rguillens.reactive.example.blog.entity.CommentEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    void findAllByPostId() {
        commentRepository.save(new CommentEntity(null, "test", "Test comment 1", 2L)).block();
        commentRepository.save(new CommentEntity(null, "test", "Test comment 2", 2L)).block();
        commentRepository.findAllByPostId(2L, PageRequest.of(0, 2))
                .as(StepVerifier::create)
                .expectNextCount(2)
                .verifyComplete();
    }
}