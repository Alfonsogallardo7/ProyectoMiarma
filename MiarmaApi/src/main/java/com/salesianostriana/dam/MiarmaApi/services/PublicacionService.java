package com.salesianostriana.dam.MiarmaApi.services;

import com.salesianostriana.dam.MiarmaApi.dto.CreatePublicacionDto;
import com.salesianostriana.dam.MiarmaApi.models.Publicacion;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PublicacionService {

    Publicacion save (CreatePublicacionDto v , MultipartFile file);
    List<Publicacion> findAll();
}
