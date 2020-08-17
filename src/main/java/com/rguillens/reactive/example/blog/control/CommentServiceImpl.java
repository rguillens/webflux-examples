package com.rguillens.reactive.example.blog.control;

import com.rguillens.reactive.example.blog.entity.CommentEntity;
import com.rguillens.reactive.example.blog.entity.dao.CommentRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CommentServiceImpl implements CommentService {

    final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Flux<CommentEntity> findAllPostComments(Long postId, Pageable pageable) {
        return commentRepository.findAllByPostId(postId, pageable);
    }

    @Override
    public Mono<CommentEntity> createPostComment(Long postId, CommentEntity comment) {
        return commentRepository.save(comment.withPostId(postId));
    }

    @Override
    public Mono<CommentEntity> updatePostComment(Long postId, CommentEntity comment) {
        return commentRepository.findById(comment.getId())
                .filter(commentEntity -> commentEntity.getPostId() == postId)
                .map(commentEntity -> commentEntity.withContent(comment.getContent()))
                .flatMap(commentRepository::save);
    }

}
