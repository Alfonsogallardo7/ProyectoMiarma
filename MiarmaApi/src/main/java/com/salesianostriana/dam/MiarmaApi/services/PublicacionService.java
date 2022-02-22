package com.salesianostriana.dam.MiarmaApi.services;

import com.salesianostriana.dam.MiarmaApi.dto.CreatePublicacionDto;
import com.salesianostriana.dam.MiarmaApi.dto.GetPublicacionDto;
import com.salesianostriana.dam.MiarmaApi.models.Privacidad;
import com.salesianostriana.dam.MiarmaApi.models.Publicacion;
import com.salesianostriana.dam.MiarmaApi.users.models.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PublicacionService {


    Publicacion savePublic (CreatePublicacionDto v , MultipartFile file, Usuario usuario);
    Publicacion savePrivate (CreatePublicacionDto v , MultipartFile file, Usuario usuario) throws IOException;
    Page<Publicacion> findAllByPrivacidad(Pageable pageable);
    Optional<Publicacion> findById (UUID id);
    Publicacion findById2(UUID id);
    Publicacion findByIdPrivacidad (UUID id);
    Optional<Usuario> findByUsername(String username);
    List<Publicacion> listFindByUsername (String username);
    GetPublicacionDto edit (UUID id, CreatePublicacionDto createPublicacionDto, MultipartFile file, Usuario usuario);
    void deleteById (UUID uuid);
}
