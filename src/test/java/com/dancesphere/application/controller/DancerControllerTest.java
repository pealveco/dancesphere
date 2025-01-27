package com.dancesphere.application.controller;

import com.dancesphere.application.service.DancerService;
import com.dancesphere.domain.dto.DancerDTO;
import com.dancesphere.domain.model.Dancer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.*;

class DancerControllerTest {

    @Mock
    private DancerService dancerService;

    @InjectMocks
    private DancerController dancerController;

    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        webTestClient = WebTestClient.bindToController(dancerController).build();
    }

    @Test
    void getAllDancers() {
        Dancer dancer1 = new Dancer();
        dancer1.setId("1");
        dancer1.setName("John Doe");
        dancer1.setStyle("Hip Hop");
        dancer1.setAge((short) 25);

        Dancer dancer2 = new Dancer();
        dancer2.setId("2");
        dancer2.setName("Jane Smith");
        dancer2.setStyle("Ballet");
        dancer2.setAge((short) 30);

        when(dancerService.getAllDancers()).thenReturn(Flux.just(dancer1, dancer2));

        webTestClient.get()
                .uri("/api/dancers")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Dancer.class)
                .hasSize(2)
                .contains(dancer1, dancer2);

        verify(dancerService, times(1)).getAllDancers();
    }

    @Test
    void getDancerById() {
        Dancer dancer = new Dancer();
        dancer.setId("1");
        dancer.setName("John Doe");
        dancer.setStyle("Hip Hop");
        dancer.setAge((short) 25);

        when(dancerService.getDancerById("1")).thenReturn(Mono.just(dancer));

        webTestClient.get()
                .uri("/api/dancers/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Dancer.class)
                .isEqualTo(dancer);

        verify(dancerService, times(1)).getDancerById("1");
    }

    @Test
    void createDancer() {
        DancerDTO dancerDTO = new DancerDTO();
        dancerDTO.setDocumentId("1234");
        dancerDTO.setName("John Doe");
        dancerDTO.setStyle("Hip Hop");
        dancerDTO.setAge((short) 25);
        dancerDTO.setEmail("johndoe@example.com");

        Dancer savedDancer = new Dancer();
        savedDancer.setId("1");
        savedDancer.setDocumentId("1234");
        savedDancer.setName("John Doe");
        savedDancer.setStyle("Hip Hop");
        savedDancer.setAge((short) 25);
        savedDancer.setEmail("johndoe@example.com");

        when(dancerService.createDancer(dancerDTO)).thenReturn(Mono.just(savedDancer));

        webTestClient.post()
                .uri("/api/dancers")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dancerDTO)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Dancer.class)
                .isEqualTo(savedDancer);

        verify(dancerService, times(1)).createDancer(dancerDTO);
    }

    @Test
    void updateDancer() {
        DancerDTO dancerDTO = new DancerDTO();
        dancerDTO.setDocumentId("1234");
        dancerDTO.setName("John Doe Updated");
        dancerDTO.setStyle("Hip Hop");
        dancerDTO.setAge((short) 26);
        dancerDTO.setEmail("johnupdated@example.com");

        Dancer updatedDancer = new Dancer();
        updatedDancer.setId("1");
        updatedDancer.setDocumentId("1234");
        updatedDancer.setName("John Doe Updated");
        updatedDancer.setStyle("Hip Hop");
        updatedDancer.setAge((short) 26);
        updatedDancer.setEmail("johnupdated@example.com");

        when(dancerService.updateDancer("1", dancerDTO)).thenReturn(Mono.just(updatedDancer));

        webTestClient.put()
                .uri("/api/dancers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dancerDTO)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Dancer.class)
                .isEqualTo(updatedDancer);

        verify(dancerService, times(1)).updateDancer("1", dancerDTO);
    }

    @Test
    void deleteDancer() {
        when(dancerService.deleteDancer("1")).thenReturn(Mono.empty());

        webTestClient.delete()
                .uri("/api/dancers/1")
                .exchange()
                .expectStatus().isOk();

        verify(dancerService, times(1)).deleteDancer("1");
    }
}
