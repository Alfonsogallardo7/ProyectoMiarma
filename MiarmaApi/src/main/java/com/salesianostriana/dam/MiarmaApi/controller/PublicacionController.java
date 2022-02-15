package com.salesianostriana.dam.MiarmaApi.controller;

import com.salesianostriana.dam.MiarmaApi.dto.CreatePublicacionDto;
import com.salesianostriana.dam.MiarmaApi.services.PublicacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PublicacionController {

    private final PublicacionService service;

    @PostMapping("/")
    public ResponseEntity<?> create (@RequestPart("file")MultipartFile file, @RequestPart("publicacion") CreatePublicacionDto newPublicacion) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.save(newPublicacion, file));
    }
}
