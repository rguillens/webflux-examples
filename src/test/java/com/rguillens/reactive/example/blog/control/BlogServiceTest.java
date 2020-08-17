package com.rguillens.reactive.example.blog.control;

import com.rguillens.reactive.example.blog.entity.BaseEntity;
import com.rguillens.reactive.example.blog.entity.BlogDto;
import com.rguillens.reactive.example.blog.entity.PostEntity;
import com.rguillens.reactive.example.blog.entity.dao.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BlogServiceTest {

    @Autowired
    PostRepository postRepository;

    @Autowired
    BlogService blogService;

    @Test
    void findAllBlogsByName() {
        blogService.findAllBlogsByName("Reactive Blog", PageRequest.of(0, 10))
                .as(StepVerifier::create)
                .expectNextMatches(dto -> dto.getId() == 1L)
                .verifyComplete();
    }

    @Test
    void createBlog() {
        blogService.createBlog(new BlogDto(null, "Test Blog", null))
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void findBlogById() {
        blogService.findBlogById(1L)
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void updateBlog() {
        blogService.updateBlog(1L, new BlogDto(null, "Reactive Blog Tested", null))
                .as(StepVerifier::create)
                .expectNextMatches(blogDto -> blogDto.getName().equals("Reactive Blog Tested"))
                .verifyComplete();
    }

    @Test
    void createBlogPost() {
        blogService.createBlog(new BlogDto(null, "Test Blog", null))
                .as(StepVerifier::create)
                .expectNextMatches(blogDto -> blogDto.getId() != null && blogDto.getName().equals("Test Blog"))
                .verifyComplete();
    }

    @Test
    void updateBlogPost() {
        PostEntity post = new PostEntity(1L,
                1L,
                "test",
                "Updated Title",
                "Updated Blog Post for testing purposes",
                "",
                false,
                null);
        blogService.updateBlogPost(1L, 1L, post)
                .as(StepVerifier::create)
                .expectNextMatches(postEntity -> postEntity.getAuthor().equals("rguillens"))
                .verifyComplete();
    }

    @Test
    void findBlogPost() {
        blogService.findBlogPost(1L, 4L)
                .as(StepVerifier::create)
                .expectNextMatches(post -> post.getId() == 4L)
                .verifyComplete();
    }

    @Test
    void publishBlogPost() {
        PostEntity post = new PostEntity(null,
                1L,
                "test",
                "Publish Title",
                "Publish Blog Post for testing purposes",
                "",
                false,
                null);

        Long postId = postRepository.save(post)
                .map(BaseEntity::getId)
                .block();
        blogService.publishBlogPost(1L, postId)
                .as(StepVerifier::create)
                .expectNextMatches(postEntity -> postEntity.getPublished() && postEntity.getPublishedAt() != null)
                .verifyComplete();
    }
}