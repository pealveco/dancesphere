package com.dancesphere.dancer.domain.ports;

import com.dancesphere.dancer.domain.model.Dancer;
import java.util.List;
import java.util.Optional;

public interface DancerRepository {

    Dancer save(Dancer dancer);

    Optional<Dancer> findById(Long id);

    List<Dancer> findAll();

    void deleteById(Long id);

    Optional<Dancer> findByEmail(String email);
}