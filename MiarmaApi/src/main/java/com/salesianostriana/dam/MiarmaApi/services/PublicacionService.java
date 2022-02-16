package com.salesianostriana.dam.MiarmaApi.services;

import com.salesianostriana.dam.MiarmaApi.dto.CreatePublicacionDto;
import com.salesianostriana.dam.MiarmaApi.models.Privacidad;
import com.salesianostriana.dam.MiarmaApi.models.Publicacion;
import com.salesianostriana.dam.MiarmaApi.users.models.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PublicacionService {


    Publicacion savePublic (CreatePublicacionDto v , MultipartFile file, Usuario usuario);
    Publicacion savePrivate (CreatePublicacionDto v , MultipartFile file, Usuario usuario);
    List<Publicacion> findAllByPrivacidad(/*Pageable pageable*/);
    Optional<Publicacion> findById (UUID id);
    /*Void deletePost (UUID id);*/
}
