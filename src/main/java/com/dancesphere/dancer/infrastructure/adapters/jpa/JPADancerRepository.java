package com.dancesphere.dancer.infrastructure.adapters.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JPADancerRepository extends JpaRepository<DancerEntity, Long> {
}
