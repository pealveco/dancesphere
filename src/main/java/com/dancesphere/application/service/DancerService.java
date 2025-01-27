package com.dancesphere.application.service;

import com.dancesphere.domain.model.Dancer;
import com.dancesphere.infrastructure.persistence.repository.DancerRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DancerService {

    private final DancerRepository dancerRepository;

    public DancerService(DancerRepository dancerRepository) {
        this.dancerRepository = dancerRepository;
    }

    public Flux<Dancer> getAllDancers() {
        return dancerRepository.findAll();
    }

    public Mono<Dancer> getDancerById(Long id) {
        return dancerRepository.findById(id);
    }

    public Mono<Dancer> createDancer(Dancer dancer) {
        return dancerRepository.save(dancer);
    }

    public Mono<Dancer> updateDancer(Long id, Dancer dancer) {
        return dancerRepository.findById(id)
                .flatMap(existingDancer -> {
                    existingDancer.setName(dancer.getName());
                    existingDancer.setStyle(dancer.getStyle());
                    return dancerRepository.save(existingDancer);
                });
    }

    public Mono<Void> deleteDancer(Long id) {
        return dancerRepository.deleteById(id);
    }
}