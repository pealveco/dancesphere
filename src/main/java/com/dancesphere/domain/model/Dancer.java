package com.dancesphere.domain.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("dancers") // Nombre de la tabla
public class Dancer {
    @Id
    private Long id;
    private String name;
    private String style;
    private Integer age;
}
