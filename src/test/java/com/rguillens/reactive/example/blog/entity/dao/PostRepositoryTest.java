package com.rguillens.reactive.example.blog.entity.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import reactor.test.StepVerifier;

@SpringBootTest
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Test
    void findAllByFullTextSearch() {
        postRepository.findAllByFullTextSearch("behavior", PageRequest.of(0, 5))
                .as(StepVerifier::create)
                .expectNextMatches(postEntity -> postEntity.getId() == 2)
                .verifyComplete();
    }

    @Test
    void findByIdAndPublished() {
        postRepository.findByIdAndPublishedIsTrue(4L)
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void findAllByIdIsNotNullAndPublishedIsTrue() {
        postRepository.findAllByIdIsNotNullAndPublishedIsTrue(PageRequest.of(0, 10))
                .as(StepVerifier::create)
                .expectNextCount(4)
                .verifyComplete();
    }

    @Test
    void findByIdAndBlogId() {
        postRepository.findByIdAndBlogId(4L, 1L)
                .as(StepVerifier::create)
                .expectNextMatches(postEntity -> postEntity.getId() == 4L)
                .verifyComplete();
    }

    @Test
    void findAllByBlogId() {
        Long count = postRepository.count().block();
        postRepository.findAllByBlogId(1L)
                .as(StepVerifier::create)
                .expectNextCount(count)
                .verifyComplete();
    }
}