package com.dancesphere.dancer.application.service;

import com.dancesphere.dancer.domain.model.Dancer;
import com.dancesphere.dancer.domain.ports.DancerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DancerServiceTest {

    @Mock
    private DancerRepository dancerRepository;

    @InjectMocks
    private DancerService dancerService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createDancer() {
        Dancer dancer = new Dancer();
        dancer.setEmail("test@example.com");

        when(dancerRepository.findByEmail(dancer.getEmail())).thenReturn(Optional.empty());
        when(dancerRepository.save(dancer)).thenReturn(dancer);

        Dancer createdDancer = dancerService.createDancer(dancer);

        assertNotNull(createdDancer);
        assertEquals("test@example.com", createdDancer.getEmail());
        verify(dancerRepository, times(1)).save(dancer);
    }

    @Test
    public void getDancerById() {
        Dancer dancer = new Dancer();
        dancer.setId(1L);

        when(dancerRepository.findById(1L)).thenReturn(Optional.of(dancer));

        Optional<Dancer> foundDancer = dancerService.getDancerById(1L);

        assertTrue(foundDancer.isPresent());
        assertEquals(Long.valueOf(1L), foundDancer.get().getId());
    }

    @Test
    public void getAllDancers() {
        Dancer dancer = new Dancer();
        when(dancerRepository.findAll()).thenReturn(Collections.singletonList(dancer));

        List<Dancer> dancers = dancerService.getAllDancers();

        assertFalse(dancers.isEmpty());
        assertEquals(1, dancers.size());
    }

    @Test
    public void updateDancer() {
        Dancer existingDancer = new Dancer();
        existingDancer.setId(1L);
        existingDancer.setEmail("existing@example.com");

        Dancer updatedDancer = new Dancer();
        updatedDancer.setEmail("updated@example.com");

        when(dancerRepository.findById(1L)).thenReturn(Optional.of(existingDancer));
        when(dancerRepository.save(existingDancer)).thenReturn(existingDancer);

        Optional<Dancer> result = dancerService.updateDancer(1L, updatedDancer);

        assertTrue(result.isPresent());
        assertEquals("updated@example.com", result.get().getEmail());
    }

    @Test
    public void deleteDancer() {
        Dancer dancer = new Dancer();
        dancer.setId(1L);

        when(dancerRepository.findById(1L)).thenReturn(Optional.of(dancer));
        doNothing().when(dancerRepository).deleteById(1L);

        boolean isDeleted = dancerService.deleteDancer(1L);

        assertTrue(isDeleted);
        verify(dancerRepository, times(1)).deleteById(1L);
    }
}