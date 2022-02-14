package com.salesianostriana.dam.MiarmaApi.users.dto;

import com.salesianostriana.dam.MiarmaApi.users.models.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDtoConvert {

public GetUsuarioDto converterUsuarioToUsuarioDto (Usuario usuario) {

    return GetUsuarioDto.builder()
            .id(usuario.getId())
            .nombre(usuario.getNombre())
            .apellidos(usuario.getApellidos())
            .username(usuario.getUsername())
            .fotoPerfil(usuario.getFotoPerfil())
            .email(usuario.getEmail())
            .rol(usuario.getRole().name()).build();
}
}
