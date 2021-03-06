package com.salesianostriana.dam.MiarmaApi.controller;

import com.salesianostriana.dam.MiarmaApi.dto.CreatePublicacionDto;
import com.salesianostriana.dam.MiarmaApi.dto.GetPublicacionDto;
import com.salesianostriana.dam.MiarmaApi.dto.PublicacionDtoConverter;
import com.salesianostriana.dam.MiarmaApi.models.Publicacion;
import com.salesianostriana.dam.MiarmaApi.repository.PublicacionesRepository;
import com.salesianostriana.dam.MiarmaApi.security.jwt.JwtUserResponse;
import com.salesianostriana.dam.MiarmaApi.services.PublicacionService;
import com.salesianostriana.dam.MiarmaApi.services.StorageService;
import com.salesianostriana.dam.MiarmaApi.services.impl.FileSystemStorageService;
import com.salesianostriana.dam.MiarmaApi.users.dto.GetUsuarioDto;
import com.salesianostriana.dam.MiarmaApi.users.dto.UsuarioDtoConvert;
import com.salesianostriana.dam.MiarmaApi.users.models.Usuario;
import com.salesianostriana.dam.MiarmaApi.utils.paginations.PaginationsLinksUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
    private final FileSystemStorageService fileSystemStorageService;
    private final PublicacionService service;
    private final PublicacionDtoConverter publicacionDtoConverter;
    private final UsuarioDtoConvert usuarioDtoConvert;
    private final PublicacionesRepository publicacionesRepository;
    private final PaginationsLinksUtils paginationsLinksUtils;


    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createPublic (@RequestPart("newPublicacion") CreatePublicacionDto newPublicacion, @RequestPart("file")MultipartFile file, @AuthenticationPrincipal Usuario usuario) {
        Publicacion publicacion = service.savePublic(newPublicacion, file, usuario);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(publicacionDtoConverter.convertPublicacionToPublicacionDto(publicacion));

    }

    @PostMapping(value = "/private", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createPrivate (@RequestPart("newPublicacion") CreatePublicacionDto newPublicacion, @RequestPart("file")MultipartFile file, @AuthenticationPrincipal Usuario usuario) throws IOException {
        Publicacion publicacion = service.savePrivate(newPublicacion, file, usuario);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(publicacionDtoConverter.convertPublicacionToPublicacionDto(publicacion));

    }

    @GetMapping("/public")
    public ResponseEntity<Page<GetPublicacionDto>> findAllByPrivacidad(@PageableDefault(size=10, page=0) Pageable pageable, HttpServletRequest request) {
        Page<Publicacion> publicacion = service.findAllByPrivacidad(pageable);

        if (publicacion.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {

            Page<GetPublicacionDto> result =
                    publicacion.map(publicacionDtoConverter::convertPublicacionToPublicacionDto);

            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

            return ResponseEntity
                    .ok().header("Link" ,
                            paginationsLinksUtils.createLinkHeader(result , uriBuilder))
                    .body(result);
            /*return ResponseEntity.ok()
                    .body(publicacion.stream()
                            .map(publicacionDtoConverter::convertPublicacionToPublicacionDto)
                            .collect(Collectors.toList()));*/
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetPublicacionDto> findById (@PathVariable UUID id) {
        //if (usuario.getRole().equals(UserRole.ADMIN) || usuario.getId().equals(id)) {
        Publicacion publicacionBuscada = service.findByIdPrivacidad(id);
            if (publicacionBuscada == null)
                return ResponseEntity.notFound().build();
            else {
                return ResponseEntity.ok()
                        .body(publicacionDtoConverter.convertPublicacionToPublicacionDto(publicacionBuscada));
            }
        //}
        //return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetPublicacionDto> edit (@PathVariable UUID id, @AuthenticationPrincipal Usuario usuario, @RequestPart("newPublicacion")CreatePublicacionDto publicacion, @RequestPart("file") MultipartFile file) {
        GetPublicacionDto publicacionBuscada = service.edit(id, publicacion, file, usuario);
        if (publicacionBuscada == null)
            return ResponseEntity.notFound().build();
        else {
            return ResponseEntity.ok()
                    .body(publicacionBuscada);
        }
    }

   @GetMapping("/")
    public ResponseEntity<List<GetPublicacionDto>> findByUsername (@RequestParam(name = "username") String username) {
        List<Publicacion> publicacion = service.listFindByUsername(username);

        if (publicacion == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok()
                    .body(publicacion.stream()
                            .map(publicacionDtoConverter::convertPublicacionToPublicacionDto)
                            .collect(Collectors.toList()));
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable UUID id, @AuthenticationPrincipal Usuario usuario) {
        Optional<Publicacion> publicacionBuscada = service.findById(id);

        if (publicacionBuscada.isEmpty())
            return ResponseEntity.notFound().build();
        else
            fileSystemStorageService.deleteFile(publicacionBuscada.get().getFichero());
            service.deleteById(id);
            return ResponseEntity.noContent().build();
    }
}
