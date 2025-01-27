package com.dancesphere.application.controller;

import com.dancesphere.application.service.DancerService;
import com.dancesphere.domain.dto.DancerDTO;
import com.dancesphere.domain.model.Dancer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/dancers")
public class DancerController {

    private final DancerService dancerService;

    public DancerController(DancerService dancerService) {
        this.dancerService = dancerService;
    }

    @Operation(summary = "Obtener todos los bailarines", description = "Devuelve una lista de todos los bailarines.")
    @GetMapping
    public Flux<Dancer> getAllDancers() {
        return dancerService.getAllDancers();
    }

    @Operation(summary = "Obtener un bailarín por ID", description = "Devuelve la información de un bailarín específico.")
    @GetMapping("/{id}")
    public Mono<Dancer> getDancerById(@Parameter(description = "ID del bailarín") @PathVariable String id) {
        return dancerService.getDancerById(id);
    }

    @Operation(summary = "Crear un nuevo bailarín", description = "Agrega un nuevo bailarín al sistema.")
    @PostMapping
    public Mono<Dancer> createDancer(@Valid @RequestBody DancerDTO dancerDTO) {
        return dancerService.createDancer(dancerDTO);
    }

    @Operation(summary = "Actualizar un bailarín", description = "Actualiza la información de un bailarín existente.")
    @PutMapping("/{id}")
    public Mono<Dancer> updateDancer(@PathVariable String id, @Valid @RequestBody DancerDTO dancerDTO) {
        return dancerService.updateDancer(id, dancerDTO);
    }


    @Operation(summary = "Eliminar un bailarín", description = "Elimina un bailarín del sistema.")
    @DeleteMapping("/{id}")
    public Mono<Void> deleteDancer(@Parameter(description = "ID del bailarín") @PathVariable String id) {
        return dancerService.deleteDancer(id);
    }
}
