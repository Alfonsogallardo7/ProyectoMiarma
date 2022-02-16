package com.salesianostriana.dam.MiarmaApi.controller;

import com.salesianostriana.dam.MiarmaApi.dto.CreatePublicacionDto;
import com.salesianostriana.dam.MiarmaApi.dto.GetPublicacionDto;
import com.salesianostriana.dam.MiarmaApi.dto.PublicacionDtoConverter;
import com.salesianostriana.dam.MiarmaApi.models.Publicacion;
import com.salesianostriana.dam.MiarmaApi.repository.PublicacionesRepository;
import com.salesianostriana.dam.MiarmaApi.services.PublicacionService;
import com.salesianostriana.dam.MiarmaApi.services.StorageService;
import com.salesianostriana.dam.MiarmaApi.users.dto.GetUsuarioDto;
import com.salesianostriana.dam.MiarmaApi.users.models.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.awt.print.Pageable;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PublicacionController {

    private final StorageService storageService;
    private final PublicacionService service;
    private final PublicacionDtoConverter publicacionDtoConverter;
    private final PublicacionesRepository publicacionesRepository;

    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createPublic (@RequestPart("newPublicacion") CreatePublicacionDto newPublicacion, @RequestPart("file")MultipartFile file, @AuthenticationPrincipal Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.savePublic(newPublicacion, file, usuario));

    }

    @GetMapping("/public")
    public ResponseEntity<List<GetPublicacionDto>> findAllByPrivacidad(/*@PageableDefault(size = 10 , page = 0)Pageable pageable*/) {
        List<Publicacion> publicacion = service.findAllByPrivacidad(/*pageable*/);

        if (publicacion == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok()
                    .body(publicacion.stream()
                            .map(publicacionDtoConverter::convertPublicacionToPublicacionDto)
                            .collect(Collectors.toList()));
        }
    }

   /* @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletePost(UUID id) {


    }*/
}
