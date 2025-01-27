package com.dancesphere.domain.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DancerDTO {

    @NotBlank(message = "El ID del documento es obligatorio")
    private String documentId;

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @NotBlank(message = "El estilo es obligatorio")
    private String style;

    @Min(value = 1, message = "La edad debe ser mayor a 0")
    @Max(value = 100, message = "La edad debe ser menor o igual a 100")
    private Short age;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El formato del email es inválido")
    private String email;
}
