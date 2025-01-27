package com.dancesphere.application.service;

import com.dancesphere.domain.dto.DancerDTO;
import com.dancesphere.domain.model.Dancer;
import com.dancesphere.domain.repository.DancerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DancerServiceTest {

    @Mock
    private DancerRepository dancerRepository;

    @InjectMocks
    private DancerService dancerService;

    private DancerDTO dancerDTO;
    private Dancer dancer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        dancerDTO = new DancerDTO("123", "John Doe", "Hip-Hop", (short) 25, "john@example.com");
        dancer = new Dancer();
        dancer.setDocumentId("123");
        dancer.setName("John Doe");
        dancer.setStyle("Hip-Hop");
        dancer.setAge((short) 25);
        dancer.setEmail("john@example.com");
    }

    @Test
    void getAllDancers() {
        when(dancerRepository.findAll()).thenReturn(Flux.just(dancer));

        StepVerifier.create(dancerService.getAllDancers())
                .expectNext(dancer)
                .verifyComplete();

        verify(dancerRepository, times(1)).findAll();
    }

    @Test
    void getDancerById() {
        when(dancerRepository.findById("123")).thenReturn(Mono.just(dancer));

        StepVerifier.create(dancerService.getDancerById("123"))
                .expectNext(dancer)
                .verifyComplete();

        verify(dancerRepository, times(1)).findById("123");
    }

    @Test
    void createDancer() {
        when(dancerRepository.findByDocumentId(any())).thenReturn(Mono.empty());
        when(dancerRepository.findByEmail(any())).thenReturn(Mono.empty());
        when(dancerRepository.save(any(Dancer.class))).thenReturn(Mono.just(dancer));

        StepVerifier.create(dancerService.createDancer(dancerDTO))
                .expectNext(dancer)
                .verifyComplete();

        verify(dancerRepository, times(1)).findByDocumentId(any());
        verify(dancerRepository, times(1)).findByEmail(any());
        verify(dancerRepository, times(1)).save(any(Dancer.class));
    }


    @Test
    void updateDancer() {
        when(dancerRepository.findById("123")).thenReturn(Mono.just(dancer));
        when(dancerRepository.save(any(Dancer.class))).thenReturn(Mono.just(dancer));

        StepVerifier.create(dancerService.updateDancer("123", dancerDTO))
                .expectNext(dancer)
                .verifyComplete();

        verify(dancerRepository, times(1)).findById("123");
        verify(dancerRepository, times(1)).save(any(Dancer.class));
    }

    @Test
    void deleteDancer() {
        when(dancerRepository.deleteById("123")).thenReturn(Mono.empty());

        StepVerifier.create(dancerService.deleteDancer("123"))
                .verifyComplete();

        verify(dancerRepository, times(1)).deleteById("123");
    }
}
