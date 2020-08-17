package com.rguillens.reactive.example.blog.entity.dao;

import com.rguillens.reactive.example.blog.entity.PostEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PostRepository extends ReactiveCrudRepository<PostEntity, Long> {
    @Query("select * " +
            "from " +
            "posts p " +
            "where " +
            "p.published = true " +
            "and :term is null or p.content like concat('%', :term, '%')")
    Flux<PostEntity> findAllByFullTextSearch(String term, Pageable pageable);

    Mono<PostEntity> findByIdAndPublishedIsTrue(Long blogId);

    Flux<PostEntity> findAllByIdIsNotNullAndPublishedIsTrue(Pageable pageable);

    Mono<PostEntity> findByIdAndBlogId(Long postId, Long blogId);

    Flux<PostEntity> findAllByBlogId(Long id);
}
