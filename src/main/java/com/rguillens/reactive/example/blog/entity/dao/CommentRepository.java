package com.rguillens.reactive.example.blog.entity.dao;

import com.rguillens.reactive.example.blog.entity.CommentEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface CommentRepository extends ReactiveCrudRepository<CommentEntity, Long> {
    Flux<CommentEntity> findAllByPostId(Long postId, Pageable pageable);
}
