package com.salesianostriana.dam.MiarmaApi.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor @AllArgsConstructor
@SuperBuilder
@Getter @Setter
public class CreatePublicacionDto {

    private String titulo, texto, fichero, privacidad;
}
