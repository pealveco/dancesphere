package com.dancesphere.dancer.infrastructure.adapters;

import com.dancesphere.dancer.domain.model.Dancer;
import com.dancesphere.dancer.domain.ports.DancerRepository;
import com.dancesphere.dancer.infrastructure.adapters.jpa.DancerEntity;
import com.dancesphere.dancer.infrastructure.adapters.jpa.JPADancerRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class DancerRepositoryImpl implements DancerRepository {

    private final JPADancerRepository jpaDancerRepository;

    public DancerRepositoryImpl(JPADancerRepository jpaDancerRepository) {
        this.jpaDancerRepository = jpaDancerRepository;
    }

    @Override
    public Dancer save(Dancer dancer) {
        DancerEntity entity = mapToEntity(dancer);
        entity = jpaDancerRepository.save(entity);
        return mapToDomain(entity);
    }

    @Override
    public Optional<Dancer> findById(Long id) {
        return jpaDancerRepository.findById(id)
                .map(this::mapToDomain);
    }

    @Override
    public List<Dancer> findAll() {
        return jpaDancerRepository.findAll()
                .stream()
                .map(this::mapToDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        jpaDancerRepository.deleteById(id);
    }

    @Override
    public Optional<Dancer> findByEmail(String email) {
        return jpaDancerRepository.findByEmail(email)
                .map(this::mapToDomain);
    }

    private DancerEntity mapToEntity(Dancer domain) {
        DancerEntity entity = new DancerEntity();
        if (domain.getId() != null) {
            entity.setId(domain.getId());
        }
        entity.setName(domain.getName());
        entity.setStyle(domain.getStyle());
        entity.setExperienceYears(domain.getExperienceYears());
        entity.setEmail(domain.getEmail());
        entity.setCreatedAt(domain.getCreatedAt() != null ? domain.getCreatedAt() : LocalDateTime.now());
        entity.setUpdatedAt(domain.getUpdatedAt() != null ? domain.getUpdatedAt() : LocalDateTime.now());
        return entity;
    }

    private Dancer mapToDomain(DancerEntity entity) {
        Dancer domain = new Dancer();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        domain.setStyle(entity.getStyle());
        domain.setExperienceYears(entity.getExperienceYears());
        domain.setEmail(entity.getEmail());
        domain.setCreatedAt(entity.getCreatedAt() != null ? entity.getCreatedAt() : LocalDateTime.now());
        domain.setUpdatedAt(entity.getUpdatedAt() != null ? entity.getUpdatedAt() : LocalDateTime.now());
        return domain;
    }
}