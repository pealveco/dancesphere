package com.dancesphere.application.controller;

import com.dancesphere.application.service.DancerService;
import com.dancesphere.domain.model.Dancer;
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

    @GetMapping
    public Flux<Dancer> getAllDancers() {
        return dancerService.getAllDancers();
    }

    @GetMapping("/{id}")
    public Mono<Dancer> getDancerById(@PathVariable Long id) {
        return dancerService.getDancerById(id);
    }

    @PostMapping
    public Mono<Dancer> createDancer(@RequestBody Dancer dancer) {
        return dancerService.createDancer(dancer);
    }

    @PutMapping("/{id}")
    public Mono<Dancer> updateDancer(@PathVariable Long id, @RequestBody Dancer dancer) {
        return dancerService.updateDancer(id, dancer);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteDancer(@PathVariable Long id) {
        return dancerService.deleteDancer(id);
    }
}