package com.rguillens.reactive.example.blog.entity.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import reactor.test.StepVerifier;

@SpringBootTest
class BlogRepositoryTest {

    @Autowired
    BlogRepository blogRepository;

    @Test
    void findAllByNameContains() {
        blogRepository.findAllByNameContains("reactive", PageRequest.of(0, 10))
                .as(StepVerifier::create)
                .expectNext()
                .verifyComplete();
    }
}