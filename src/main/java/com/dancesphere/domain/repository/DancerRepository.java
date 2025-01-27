package com.dancesphere.domain.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import com.dancesphere.domain.model.Dancer;
import reactor.core.publisher.Flux;

public interface DancerRepository extends ReactiveCrudRepository<Dancer, Long> {
    Flux<Dancer> findByStyle(String style);
}