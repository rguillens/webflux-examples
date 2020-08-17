package com.rguillens.reactive.example.blog.control;

import com.rguillens.reactive.example.blog.entity.BlogPostDto;
import com.rguillens.reactive.example.blog.entity.PostEntity;
import com.rguillens.reactive.example.blog.entity.dao.PostRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PostServiceImpl implements PostService {

    final PostRepository postRepository;

    final DatabaseClient client;
    public PostServiceImpl(PostRepository postRepository,
                           DatabaseClient client) {
        this.postRepository = postRepository;
        this.client = client;
    }

    @Override
    public Flux<BlogPostDto> searchBlogPosts(String term, Pageable pageable) {
        return postRepository.findAllByFullTextSearch(term, pageable)
                .map(PostEntity::toDto);
    }

    @Override
    public Mono<PostEntity> findPostById(Long postId) {
        return postRepository.findByIdAndPublishedIsTrue(postId);
    }

    @Override
    public Flux<BlogPostDto> findBlogPosts(Pageable pageable) {
        return postRepository.findAllByIdIsNotNullAndPublishedIsTrue(pageable)
                .map(PostEntity::toDto);
    }
}
