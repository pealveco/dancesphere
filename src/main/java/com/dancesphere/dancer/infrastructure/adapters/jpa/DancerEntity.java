package com.dancesphere.dancer.infrastructure.adapters.jpa;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dancers", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class DancerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String style;
    private Short experienceYears;

    @Column(nullable = false, unique = true)
    private String email;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}