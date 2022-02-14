package com.salesianostriana.dam.MiarmaApi.users.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class GetUsuarioDto {

    private UUID id;
    private String nombre, apellidos, fotoPerfil, username, email, rol;
}
