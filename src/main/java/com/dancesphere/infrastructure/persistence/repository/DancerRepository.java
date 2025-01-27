package com.dancesphere.infrastructure.persistence.repository;

import com.dancesphere.domain.model.Dancer;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface DancerRepository extends R2dbcRepository<Dancer, Long> {
    Flux<Dancer> findByStyle(String style);
}