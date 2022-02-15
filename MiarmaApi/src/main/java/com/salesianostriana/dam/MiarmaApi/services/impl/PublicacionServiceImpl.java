package com.salesianostriana.dam.MiarmaApi.services.impl;

import com.salesianostriana.dam.MiarmaApi.dto.CreatePublicacionDto;
import com.salesianostriana.dam.MiarmaApi.models.Privacidad;
import com.salesianostriana.dam.MiarmaApi.models.Publicacion;
import com.salesianostriana.dam.MiarmaApi.repository.PublicacionesRepository;
import com.salesianostriana.dam.MiarmaApi.services.PublicacionService;
import com.salesianostriana.dam.MiarmaApi.services.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublicacionServiceImpl implements PublicacionService {

    private final PublicacionesRepository repository;
    private final StorageService storageService;

    @Override
    public Publicacion save(CreatePublicacionDto v, MultipartFile file) {
        String filename = storageService.store(file);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download")
                .path(filename)
                .toUriString();

        return repository.save(Publicacion.builder()
                .titulo(v.getTitulo())
                .texto(v.getTexto())
                .fichero(uri)
                .privacidad(Privacidad.PUBLICO)
                .build());
    }

    @Override
    public List<Publicacion> findAll() {
        return repository.findAll();
    }
}
