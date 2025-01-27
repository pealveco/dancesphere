package com.dancesphere.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Data;

@Data
@Table("dancer")
public class Dancer {
    @Id
    private Long id;
    private String name;
    private String style;
}