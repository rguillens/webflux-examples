package com.rguillens.reactive.example.blog.boundary;

import com.rguillens.reactive.example.blog.control.BlogService;
import com.rguillens.reactive.example.blog.entity.BlogDto;
import com.rguillens.reactive.example.blog.entity.PostEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/blogs")
public class BlogApi {

    final BlogService blogService;

    public BlogApi(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("")
    public Flux<BlogDto> findAllBlogs(@RequestParam(name = "page", defaultValue = "0") int page,
                                      @RequestParam(name = "size", defaultValue = "10") int size,
                                      @RequestParam(required = false, defaultValue = "") String name) {
        return blogService.findAllBlogsByName(name, PageRequest.of(page, size));
    }

    @PostMapping("")
    public Mono<BlogDto> findBlog(@Valid @RequestBody BlogDto blog) {
        return blogService.createBlog(blog);
    }

    @GetMapping("/{blogId}")
    public Mono<BlogDto> findBlog(@PathVariable("blogId") Long blogId) {
        return blogService.findBlogById(blogId);
    }

    @PutMapping("/{blogId}")
    public Mono<BlogDto> updateBlog(@PathVariable("blogId") Long blogId,
                                    @Valid @RequestBody BlogDto blog) {
        return blogService.updateBlog(blogId, blog);
    }

    @PostMapping("/{blogId}/posts")
    public Mono<PostEntity> createBlogPost(@PathVariable("blogId") Long blogId,
                                           @Valid @RequestBody PostEntity post) {
        return blogService.createBlogPost(blogId, post);
    }

    @PutMapping("/{blogId}/posts/{postId}")
    public Mono<PostEntity> updateBlogPost(@PathVariable("blogId") Long blogId,
                                           @PathVariable("postId") Long postId,
                                           @Valid @RequestBody PostEntity post) {
        return blogService.updateBlogPost(blogId, postId, post);
    }

    @PutMapping("/{blogId}/posts/{postId}/publish")
    public Mono<PostEntity> publishBlogPost(@PathVariable("blogId") Long blogId,
                                           @PathVariable("postId") Long postId) {
        return blogService.publishBlogPost(blogId, postId);
    }

    @GetMapping("/{blogId}/posts/{postId}")
    public Mono<PostEntity> findBlogPost(@PathVariable("blogId") Long blogId,
                                         @PathVariable("postId") Long postId) {
        return blogService.findBlogPost(blogId, postId);
    }

}
