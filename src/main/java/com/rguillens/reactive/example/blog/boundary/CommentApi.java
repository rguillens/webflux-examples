package com.rguillens.reactive.example.blog.boundary;

import com.rguillens.reactive.example.blog.control.CommentService;
import com.rguillens.reactive.example.blog.entity.CommentEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/posts/")
public class CommentApi {

    final CommentService commentService;

    public CommentApi(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{postId}/comments")
    public Flux<CommentEntity> findAllPostComments(@RequestParam(name = "page", defaultValue = "0") int page,
                                                   @RequestParam(name = "size", defaultValue = "10") int size,
                                                   @PathVariable("postId") Long postId) {
        return commentService.findAllPostComments(postId, PageRequest.of(page, size));
    }

    @PostMapping("/{postId}/comments")
    public Mono<CommentEntity> createComment(@PathVariable("postId") Long postId,
                                             @Valid @RequestBody CommentEntity comment) {
        return commentService.createPostComment(postId, comment);
    }

    @PutMapping("/{postId}/comments")
    public Mono<CommentEntity> updateComment(@PathVariable("postId") Long postId,
                                             @Valid @RequestBody CommentEntity comment) {
        return commentService.updatePostComment(postId, comment);
    }
}
