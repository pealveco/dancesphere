package com.dancesphere.dancer.controller;

import com.dancesphere.dancer.application.service.DancerService;
import com.dancesphere.dancer.domain.model.Dancer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/dancers")
@Api(value = "Dancer Management System", tags = "Dancer Management")
public class DancerController {

    private final DancerService dancerService;

    public DancerController(DancerService dancerService) {
        this.dancerService = dancerService;
    }

    @PostMapping
    @ApiOperation(value = "Create a new dancer", response = Dancer.class)
    public ResponseEntity<Dancer> createDancer(@Valid @RequestBody Dancer dancer) {
        Dancer created = dancerService.createDancer(dancer);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a dancer by ID", response = Dancer.class)
    public ResponseEntity<Dancer> getDancerById(@PathVariable Long id) {
        return dancerService.getDancerById(id)
                .map(d -> new ResponseEntity<>(d, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    @ApiOperation(value = "Get all dancers", response = List.class)
    public ResponseEntity<List<Dancer>> getAllDancers() {
        List<Dancer> dancers = dancerService.getAllDancers();
        return new ResponseEntity<>(dancers, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a dancer", response = Dancer.class)
    public ResponseEntity<Dancer> updateDancer(@PathVariable Long id, @Valid @RequestBody Dancer dancer) {
        return dancerService.updateDancer(id, dancer)
                .map(updated -> new ResponseEntity<>(updated, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a dancer")
    public ResponseEntity<Void> deleteDancer(@PathVariable Long id) {
        boolean deleted = dancerService.deleteDancer(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}