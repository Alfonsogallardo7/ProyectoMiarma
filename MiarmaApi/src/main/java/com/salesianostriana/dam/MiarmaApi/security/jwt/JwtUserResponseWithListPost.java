package com.salesianostriana.dam.MiarmaApi.security.jwt;

import com.salesianostriana.dam.MiarmaApi.dto.GetPublicacionDto;
import com.salesianostriana.dam.MiarmaApi.models.Publicacion;
import lombok.*;

import java.util.List;

@Builder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class JwtUserResponseWithListPost {

    private String nombre, apellidos, username, email, fotoPerfil, rol, token, privacidad;

    List<GetPublicacionDto> listaPublicaciones;
}
