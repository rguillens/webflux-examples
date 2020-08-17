package com.rguillens.reactive.example.blog.entity.dao;

import com.rguillens.reactive.example.blog.entity.BlogEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface BlogRepository extends ReactiveCrudRepository<BlogEntity, Long> {
    Flux<BlogEntity> findAllByNameContains(String name, Pageable pageable);
}
