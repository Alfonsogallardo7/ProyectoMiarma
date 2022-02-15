package com.salesianostriana.dam.MiarmaApi.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@Value
@SuperBuilder
@Getter @Setter
public class CreatePublicacionDto {

    String titulo, texto, fichero, privacidad;
}
