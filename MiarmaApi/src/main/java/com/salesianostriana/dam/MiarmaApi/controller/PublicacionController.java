package com.salesianostriana.dam.MiarmaApi.controller;

import com.salesianostriana.dam.MiarmaApi.dto.CreatePublicacionDto;
import com.salesianostriana.dam.MiarmaApi.models.Publicacion;
import com.salesianostriana.dam.MiarmaApi.services.PublicacionService;
import com.salesianostriana.dam.MiarmaApi.services.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PublicacionController {

    private final StorageService storageService;

    private final PublicacionService service;

    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> create (@RequestPart("newPublicacion") CreatePublicacionDto newPublicacion, @RequestPart("file")MultipartFile file) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.save(newPublicacion, file));

    }
}
