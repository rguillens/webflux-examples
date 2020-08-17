package com.rguillens.reactive.example.blog.control;

import com.rguillens.reactive.example.blog.entity.BlogPostDto;
import com.rguillens.reactive.example.blog.entity.PostEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PostService {
    Flux<BlogPostDto> searchBlogPosts(String term, Pageable pageable);

    Mono<PostEntity> findPostById(Long postId);

    Flux<BlogPostDto> findBlogPosts(Pageable pageable);
}
