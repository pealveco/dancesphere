package com.dancesphere.dancer.infrastructure.adapters.jpa;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "dancers")
public class DancerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String style;
    private Short experienceYears;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public DancerEntity() {
        // TODO document why this constructor is empty
    }

    public void setExperienceYears(Short experienceYears) { this.experienceYears = experienceYears; }

}
