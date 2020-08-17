package com.rguillens.reactive.example.blog.boundary;

import com.rguillens.reactive.example.blog.control.PostService;
import com.rguillens.reactive.example.blog.entity.BlogPostDto;
import com.rguillens.reactive.example.blog.entity.PostEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/posts")
public class PostApi {
    final PostService postService;

    public PostApi(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("")
    public Flux<BlogPostDto> findBlogPosts(@RequestParam(name = "page", defaultValue = "0") int page,
                                           @RequestParam(name = "size", defaultValue = "10") int size) {

        return postService.findBlogPosts(PageRequest.of(page, size));
    }

    @GetMapping("/{postId}")
    public Mono<PostEntity> findBlogPostById(@PathVariable("postId") Long postId) {
        return postService.findPostById(postId);
    }

    @GetMapping("/search")
    public Flux<BlogPostDto> searchBlogPosts(@RequestParam(name = "page", defaultValue = "0") int page,
                                             @RequestParam(name = "size", defaultValue = "10") int size,
                                             @RequestParam(name = "term", required = false, defaultValue = "") String term) {

        return postService.searchBlogPosts(term, PageRequest.of(page, size));
    }
}
