package com.dancesphere.dancer.application.service;

import com.dancesphere.dancer.domain.model.Dancer;
import com.dancesphere.dancer.domain.ports.DancerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DancerService {

    private final DancerRepository dancerRepository;

    public DancerService(DancerRepository dancerRepository) {
        this.dancerRepository = dancerRepository;
    }

    /**
     * Crear un nuevo Dancer
     */
    public Dancer createDancer(Dancer dancer) {
        dancer.setCreatedAt(LocalDateTime.now());
        dancer.setUpdatedAt(LocalDateTime.now());
        return dancerRepository.save(dancer);
    }

    /**
     * Obtener un Dancer por ID
     */
    public Optional<Dancer> getDancerById(Long id) {
        return dancerRepository.findById(id);
    }

    /**
     * Listar todos los Dancers
     */
    public List<Dancer> getAllDancers() {
        return dancerRepository.findAll();
    }

    /**
     * Actualizar un Dancer
     */
    public Optional<Dancer> updateDancer(Long id, Dancer updatedDancer) {
        Optional<Dancer> existing = dancerRepository.findById(id);
        if (existing.isPresent()) {
            Dancer dancer = existing.get();
            dancer.setName(updatedDancer.getName());
            dancer.setStyle(updatedDancer.getStyle());
            dancer.setExperienceYears(updatedDancer.getExperienceYears());
            dancer.setUpdatedAt(LocalDateTime.now());

            return Optional.of(dancerRepository.save(dancer));
        }
        return Optional.empty();
    }

    /**
     * Eliminar un Dancer
     */
    public boolean deleteDancer(Long id) {
        Optional<Dancer> existing = dancerRepository.findById(id);
        if (existing.isPresent()) {
            dancerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
