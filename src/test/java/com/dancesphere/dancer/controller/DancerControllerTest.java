package com.dancesphere.dancer.controller;

import com.dancesphere.dancer.application.service.DancerService;
import com.dancesphere.dancer.domain.model.Dancer;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class DancerControllerTest {

    @Mock
    private DancerService dancerService;

    @InjectMocks
    private DancerController dancerController;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateDancer() {
        Dancer dancer = new Dancer();
        when(dancerService.createDancer(any(Dancer.class))).thenReturn(dancer);

        ResponseEntity<Dancer> response = dancerController.createDancer(dancer);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(dancer, response.getBody());
    }

    @Test
    public void testGetDancerById() {
        Dancer dancer = new Dancer();
        when(dancerService.getDancerById(anyLong())).thenReturn(Optional.of(dancer));

        ResponseEntity<Dancer> response = dancerController.getDancerById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dancer, response.getBody());
    }

    @Test
    public void testGetDancerById_NotFound() {
        when(dancerService.getDancerById(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<Dancer> response = dancerController.getDancerById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetAllDancers() {
        List<Dancer> dancers = Arrays.asList(new Dancer(), new Dancer());
        when(dancerService.getAllDancers()).thenReturn(dancers);

        ResponseEntity<List<Dancer>> response = dancerController.getAllDancers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dancers, response.getBody());
    }

    @Test
    public void testUpdateDancer() {
        Dancer dancer = new Dancer();
        when(dancerService.updateDancer(anyLong(), any(Dancer.class))).thenReturn(Optional.of(dancer));

        ResponseEntity<Dancer> response = dancerController.updateDancer(1L, dancer);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dancer, response.getBody());
    }

    @Test
    public void testUpdateDancer_NotFound() {
        when(dancerService.updateDancer(anyLong(), any(Dancer.class))).thenReturn(Optional.empty());

        ResponseEntity<Dancer> response = dancerController.updateDancer(1L, new Dancer());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeleteDancer() {
        when(dancerService.deleteDancer(anyLong())).thenReturn(true);

        ResponseEntity<Void> response = dancerController.deleteDancer(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testDeleteDancer_NotFound() {
        when(dancerService.deleteDancer(anyLong())).thenReturn(false);

        ResponseEntity<Void> response = dancerController.deleteDancer(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}