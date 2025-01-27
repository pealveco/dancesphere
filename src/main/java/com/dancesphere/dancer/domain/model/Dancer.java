package com.dancesphere.dancer.domain.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Dancer {
    private Long id;
    private String name;
    private String style;
    private Short experienceYears;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
