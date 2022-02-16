package com.salesianostriana.dam.MiarmaApi.dto;

import com.salesianostriana.dam.MiarmaApi.models.Publicacion;
import org.springframework.stereotype.Component;

@Component
public class PublicacionDtoConverter {

    public GetPublicacionDto convertPublicacionToPublicacionDto (Publicacion publicacion) {
        return GetPublicacionDto.builder()
                .id(publicacion.getId())
                .texto(publicacion.getTexto())
                .fichero(publicacion.getFichero())
                .privacidad(publicacion.getPrivacidad().getValor())
                .usernameUsuario(publicacion.getUsuario().getUsername())
                .build();
    }
}
