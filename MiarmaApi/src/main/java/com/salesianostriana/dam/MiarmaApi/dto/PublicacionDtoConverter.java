package com.salesianostriana.dam.MiarmaApi.dto;

import com.salesianostriana.dam.MiarmaApi.models.Publicacion;
import org.springframework.stereotype.Component;

@Component
public class PublicacionDtoConverter {

    public GetPublicacionDto convertPublicacionToPublicacionDto (Publicacion publicacion) {
        return GetPublicacionDto.builder()
                .id(publicacion.getId())
                .titulo(publicacion.getTitulo())
                .texto(publicacion.getTexto())
                .fichero(publicacion.getFichero())
                .privacidad(publicacion.getPrivacidad().getValor())
                .usernameUsuario(publicacion.getUsuario().getUsername())
                .build();
    }

    public Publicacion convertPublicacionDtoToPublicacion (GetPublicacionDto publiDto, Publicacion publicacion) {
        return new Publicacion(
                publicacion.getId(),
                publiDto.getTitulo(),
                publiDto.getTexto(),
                publiDto.getFichero(),
                publicacion.getPrivacidad(),
                publicacion.getUsuario()
        );
    }
}
