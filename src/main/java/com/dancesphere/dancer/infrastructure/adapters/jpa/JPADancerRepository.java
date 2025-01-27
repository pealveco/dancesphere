package com.dancesphere.dancer.infrastructure.adapters.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JPADancerRepository extends JpaRepository<DancerEntity, Long> {
    Optional<DancerEntity> findByEmail(String email);
}
