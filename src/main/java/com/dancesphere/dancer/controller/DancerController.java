package com.dancesphere.dancer.controller;

import com.dancesphere.dancer.application.service.DancerService;
import com.dancesphere.dancer.domain.model.Dancer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dancers")
public class DancerController {

    private final DancerService dancerService;

    public DancerController(DancerService dancerService) {
        this.dancerService = dancerService;
    }

    @PostMapping
    public ResponseEntity<Dancer> createDancer(@RequestBody Dancer dancer) {
        Dancer created = dancerService.createDancer(dancer);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dancer> getDancerById(@PathVariable Long id) {
        return dancerService.getDancerById(id)
                .map(d -> new ResponseEntity<>(d, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Dancer>> getAllDancers() {
        List<Dancer> dancers = dancerService.getAllDancers();
        return new ResponseEntity<>(dancers, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dancer> updateDancer(@PathVariable Long id, @RequestBody Dancer dancer) {
        return dancerService.updateDancer(id, dancer)
                .map(updated -> new ResponseEntity<>(updated, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDancer(@PathVariable Long id) {
        boolean deleted = dancerService.deleteDancer(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
