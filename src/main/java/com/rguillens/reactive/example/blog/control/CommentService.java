package com.rguillens.reactive.example.blog.control;

import com.rguillens.reactive.example.blog.entity.CommentEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CommentService {
    Flux<CommentEntity> findAllPostComments(Long postId, Pageable pageable);

    @Transactional
    Mono<CommentEntity> createPostComment(Long postId, CommentEntity comment);

    @Transactional
    Mono<CommentEntity> updatePostComment(Long postId, CommentEntity comment);
}
