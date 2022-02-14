package com.salesianostriana.dam.MiarmaApi.security.jwt;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
public class JwtUserResponse {

    private String nombre, apellidos, username, email, fotoPerfil, rol, token;

    private boolean privacidad;
}
