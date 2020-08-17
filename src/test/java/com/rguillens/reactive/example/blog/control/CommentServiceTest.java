package com.rguillens.reactive.example.blog.control;

import com.rguillens.reactive.example.blog.entity.CommentEntity;
import com.rguillens.reactive.example.blog.entity.dao.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentServiceTest {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CommentService commentService;

    @Test
    void findAllPostComments() {
        commentRepository.save(new CommentEntity(null, "test", "Test comment 1", 1L))
        .block();
        commentRepository.save(new CommentEntity(null, "test", "Test comment 2", 1L))
        .block();
        commentService.findAllPostComments(1L, PageRequest.of(0, 5))
                .as(StepVerifier::create)
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void createPostComment() {
        commentService.createPostComment(2L,
                new CommentEntity(null, "test", "Test create comment", null))
                .as(StepVerifier::create)
                .expectNextMatches(commentEntity -> commentEntity.getPostId() == 2L)
                .verifyComplete();
    }

    @Test
    void updatePostComment() {
        Long commentId = commentRepository.save(new CommentEntity(null, "test", "Test update comment", 3L))
                .map(CommentEntity::getId)
                .block();

        commentService.updatePostComment(3L, new CommentEntity(commentId, "author", "Test comment changed", 3L))
                .as(StepVerifier::create)
                .expectNextMatches(c -> c.getAuthor().equals("test") && c.getContent().equals("Test comment changed"))
                .verifyComplete();
    }
}