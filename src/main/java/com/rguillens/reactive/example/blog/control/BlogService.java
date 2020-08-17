package com.rguillens.reactive.example.blog.control;

import com.rguillens.reactive.example.blog.entity.BlogDto;
import com.rguillens.reactive.example.blog.entity.BlogEntity;
import com.rguillens.reactive.example.blog.entity.PostEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BlogService {

    Flux<BlogDto> findAllBlogsByName(String name, Pageable pageable);

    @Transactional
    Mono<BlogDto> createBlog(BlogDto blog);

    Mono<BlogDto> findBlogById(Long blogId);

    @Transactional
    Mono<BlogDto> updateBlog(Long blogId, BlogDto blog);

    @Transactional
    Mono<PostEntity> createBlogPost(Long blogId, PostEntity post);

    @Transactional
    Mono<PostEntity> updateBlogPost(Long blogId, Long postId, PostEntity post);

    Mono<PostEntity> findBlogPost(Long blogId, Long postId);

    @Transactional
    Mono<PostEntity> publishBlogPost(Long blogId, Long postId);
}
