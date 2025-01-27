package com.dancesphere.domain.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "dancer")
public class Dancer {
    @Id
    private String id;

    @Indexed(unique = true)
    private String documentId;

    @Indexed(unique = true)
    private String email; // Nuevo campo para el email

    private String name;
    private String style;
    private Short age;
}
