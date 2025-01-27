package com.dancesphere.domain.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import com.dancesphere.domain.model.Dancer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DancerRepository extends ReactiveMongoRepository<Dancer, String> {

    Flux<Dancer> findByStyle(String style);
    Mono<Dancer> findByDocumentId(String documentId);
    Mono<Dancer> findByEmail(String email);
}
