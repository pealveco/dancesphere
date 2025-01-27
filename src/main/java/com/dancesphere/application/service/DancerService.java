package com.dancesphere.application.service;

import com.dancesphere.domain.dto.DancerDTO;
import com.dancesphere.domain.model.Dancer;
import com.dancesphere.domain.repository.DancerRepository;
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

    public Mono<Dancer> getDancerById(String id) {
        return dancerRepository.findById(id);
    }

    public Mono<Dancer> createDancer(DancerDTO dancerDTO) {
        Dancer dancer = new Dancer();
        dancer.setDocumentId(dancerDTO.getDocumentId());
        dancer.setName(dancerDTO.getName());
        dancer.setStyle(dancerDTO.getStyle());
        dancer.setAge(dancerDTO.getAge());
        dancer.setEmail(dancerDTO.getEmail()); // Asignar el email

        // Validar si ya existe un bailarín con el mismo documentId o email
        return dancerRepository.findByDocumentId(dancer.getDocumentId())
                .flatMap(existingDancer -> Mono.error(new IllegalArgumentException("El ID del documento ya está en uso")))
                .switchIfEmpty(dancerRepository.findByEmail(dancer.getEmail())
                        .flatMap(existingDancer -> Mono.error(new IllegalArgumentException("El email ya está en uso"))))
                .switchIfEmpty(dancerRepository.save(dancer))
                .cast(Dancer.class);
    }

    public Mono<Dancer> updateDancer(String id, DancerDTO dancerDTO) {
        return dancerRepository.findById(id)
                .flatMap(dancer -> {
                    dancer.setDocumentId(dancerDTO.getDocumentId());
                    dancer.setName(dancerDTO.getName());
                    dancer.setStyle(dancerDTO.getStyle());
                    dancer.setAge(dancerDTO.getAge());
                    dancer.setEmail(dancerDTO.getEmail()); // Actualizar el email
                    return dancerRepository.save(dancer);
                });
    }

    public Mono<Void> deleteDancer(String id) {
        return dancerRepository.deleteById(id);
    }
}
