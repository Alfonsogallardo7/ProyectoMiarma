package com.salesianostriana.dam.MiarmaApi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class GetPublicacionDto {

    private UUID id;
    private String titulo, texto, fichero, privacidad, usernameUsuario;
}
