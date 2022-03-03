package com.salesianostriana.dam.MiarmaApi.security.jwt;

import com.salesianostriana.dam.MiarmaApi.dto.GetPublicacionDto;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
public class JwtUserResponse {

    private String nombre, apellidos, username, email, fotoPerfil, rol, token, privacidad;
    private List<GetPublicacionDto> listaPublicaciones;


}
