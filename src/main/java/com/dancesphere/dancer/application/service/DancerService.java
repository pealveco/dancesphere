package com.dancesphere.dancer.application.service;

import com.dancesphere.dancer.application.exception.DancerNotFoundException;
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

    public Dancer createDancer(Dancer dancer) {
        if (dancer == null) {
            throw new DancerNotFoundException("Dancer cannot be null");
        }
        if (dancer.getEmail() == null || dancer.getEmail().isEmpty()) {
            throw new DancerNotFoundException("Email cannot be null or empty");
        }
        if (dancerRepository.findByEmail(dancer.getEmail()).isPresent()) {
            throw new DancerNotFoundException("Email already exists");
        }
        dancer.setCreatedAt(LocalDateTime.now());
        dancer.setUpdatedAt(LocalDateTime.now());
        return dancerRepository.save(dancer);
    }

    public Optional<Dancer> getDancerById(Long id) {
        if (id == null) {
            throw new DancerNotFoundException("ID cannot be null");
        }
        return dancerRepository.findById(id);
    }

    public List<Dancer> getAllDancers() {
        return dancerRepository.findAll();
    }

    public Optional<Dancer> updateDancer(Long id, Dancer updatedDancer) {
        if (id == null || updatedDancer == null) {
            throw new DancerNotFoundException("ID and updatedDancer cannot be null");
        }
        Optional<Dancer> existing = dancerRepository.findById(id);
        if (existing.isPresent()) {
            Dancer dancer = existing.get();
            dancer.setName(updatedDancer.getName());
            dancer.setStyle(updatedDancer.getStyle());
            dancer.setExperienceYears(updatedDancer.getExperienceYears());
            dancer.setEmail(updatedDancer.getEmail());
            dancer.setUpdatedAt(LocalDateTime.now());

            return Optional.of(dancerRepository.save(dancer));
        }
        return Optional.empty();
    }

    public boolean deleteDancer(Long id) {
        if (id == null) {
            throw new DancerNotFoundException("ID cannot be null");
        }
        Optional<Dancer> existing = dancerRepository.findById(id);
        if (existing.isPresent()) {
            dancerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}