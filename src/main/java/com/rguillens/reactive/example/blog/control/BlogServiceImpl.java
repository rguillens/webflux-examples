package com.rguillens.reactive.example.blog.control;

import com.rguillens.reactive.example.blog.entity.BlogDto;
import com.rguillens.reactive.example.blog.entity.BlogEntity;
import com.rguillens.reactive.example.blog.entity.PostEntity;
import com.rguillens.reactive.example.blog.entity.dao.BlogRepository;
import com.rguillens.reactive.example.blog.entity.dao.PostRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Service
public class BlogServiceImpl implements BlogService {

    final BlogRepository blogRepository;

    final PostRepository postRepository;

    public BlogServiceImpl(BlogRepository blogRepository, PostRepository postRepository) {
        this.blogRepository = blogRepository;
        this.postRepository = postRepository;
    }

    @Override
    public Flux<BlogDto> findAllBlogsByName(String name, Pageable pageable) {
        return blogRepository.findAllByNameContains(name, pageable)
                .flatMap(blog -> postRepository.findAllByBlogId(blog.getId())
                        .map(PostEntity::toDto)
                        .collectList()
                        .map(blog::toDto));
    }

    @Override
    public Mono<BlogDto> createBlog(BlogDto blog) {
        return Mono.justOrEmpty(blog)
                .map(BlogEntity::new)
                .flatMap(blogRepository::save)
                .map(blogEntity -> blogEntity.toDto(Collections.emptyList()));

    }

    @Override
    public Mono<BlogDto> findBlogById(Long blogId) {
        return blogRepository.findById(blogId)
                .flatMap(blog -> postRepository.findAllByBlogId(blog.getId())
                        .map(PostEntity::toDto)
                        .collectList()
                        .map(blog::toDto));
    }

    @Override
    public Mono<BlogDto> updateBlog(Long blogId, BlogDto blog) {
        return blogRepository.findById(blogId)
                .map(blogEntity -> blogEntity.withName(blog.getName()))
                .flatMap(blogRepository::save)
                .map(blogEntity -> blogEntity.toDto(blog.getPosts()));
    }

    @Override
    public Mono<PostEntity> createBlogPost(Long blogId, PostEntity post) {
        return Mono.just(post)
                .map(postEntity -> postEntity.withBlogId(blogId))
                .flatMap(postRepository::save);
    }

    @Override
    public Mono<PostEntity> updateBlogPost(Long blogId, Long postId, PostEntity post) {
        return findBlogPost(postId, blogId)
                .map(postEntity -> {
                    postEntity.withTitleAndSummaryAndContent(post.getTitle(),
                                    post.getSummary(),
                                    post.getContent());

                    return postEntity.withPublished(post.getPublished());
                })
                .flatMap(postRepository::save);
    }

    @Override
    public Mono<PostEntity> findBlogPost(Long blogId, Long postId) {
        return postRepository.findByIdAndBlogId(postId, blogId);
    }

    @Override
    public Mono<PostEntity> publishBlogPost(Long blogId, Long postId) {
        return postRepository.findByIdAndBlogId(postId, blogId)
                .map(postEntity -> postEntity.withPublished(true))
                .flatMap(postRepository::save);
    }

}
