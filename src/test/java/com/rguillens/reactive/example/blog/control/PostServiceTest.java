package com.rguillens.reactive.example.blog.control;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServiceTest {

    @Autowired
    PostService postService;

    @Test
    void searchBlogPosts() {
        postService.searchBlogPosts("behavior", PageRequest.of(0, 5))
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void findPostById() {
        postService.findPostById(4L)
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void findBlogPosts() {
        postService.findBlogPosts(PageRequest.of(0, 2))
                .as(StepVerifier::create)
                .expectNextCount(2)
                .verifyComplete();

        postService.findBlogPosts(PageRequest.of(0, 10))
                .as(StepVerifier::create)
                .expectNextCount(4)
                .verifyComplete();
    }
}