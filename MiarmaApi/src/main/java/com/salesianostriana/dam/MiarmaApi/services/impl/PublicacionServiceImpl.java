package com.salesianostriana.dam.MiarmaApi.services.impl;

import com.salesianostriana.dam.MiarmaApi.dto.CreatePublicacionDto;
import com.salesianostriana.dam.MiarmaApi.dto.GetPublicacionDto;
import com.salesianostriana.dam.MiarmaApi.dto.PublicacionDtoConverter;
import com.salesianostriana.dam.MiarmaApi.models.Privacidad;
import com.salesianostriana.dam.MiarmaApi.models.Publicacion;
import com.salesianostriana.dam.MiarmaApi.repository.PublicacionesRepository;
import com.salesianostriana.dam.MiarmaApi.services.PublicacionService;
import com.salesianostriana.dam.MiarmaApi.services.StorageService;
import com.salesianostriana.dam.MiarmaApi.users.models.Usuario;
import com.salesianostriana.dam.MiarmaApi.users.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.awt.print.Pageable;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PublicacionServiceImpl implements PublicacionService {

    private final PublicacionesRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final StorageService storageService;
    private final FileSystemStorageService fileSystemStorageService;
    private final PublicacionDtoConverter publicacionDtoConverter;

    @Override
    public Publicacion savePublic(CreatePublicacionDto v, MultipartFile file, Usuario usuario) {
        String filename = storageService.store(file);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(filename)
                .toUriString();


        Publicacion post1 = Publicacion.builder()
                .titulo(v.getTitulo())
                .texto(v.getTexto())
                .fichero(uri)
                .privacidad(Privacidad.PUBLICO)
                .usuario(usuario)
                .build();

        return repository.save(post1);


    }

    @Override
    public Publicacion savePrivate (CreatePublicacionDto v, MultipartFile file, Usuario usuario) {
        String filename = storageService.store(file);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(filename)
                .toUriString();

        return repository.save(Publicacion.builder()
                .titulo(v.getTitulo())
                .texto(v.getTexto())
                .fichero(uri)
                .privacidad(Privacidad.PRIVADO)
                .usuario(usuario)
                .build());
    }

    @Override
    public List<Publicacion> findAllByPrivacidad(/*Pageable pageable*/) {
        if (repository.findAllByPrivacidad(Privacidad.PUBLICO).isEmpty())
            return null;
        else
            return repository.findAllByPrivacidad(Privacidad.PUBLICO).get();
    }

    @Override
    public Publicacion findByIdPrivacidad(UUID id) {
        Optional<Publicacion> publicacion = repository.findById(id);

        if (publicacion.isEmpty()){
            return null;
        }else if (publicacion.get().getPrivacidad().equals(Privacidad.PUBLICO)) {
            return publicacion.get();
        }
        return null;
    }

    @Override
    public List<Publicacion> listFindByUsername(String username) {
        if (usuarioRepository.findAllByUsername(username).isEmpty())
            return null;
        else
            return usuarioRepository.findAllByUsername(username).get().getListaPublicaciones();
    }

    @Override
    public GetPublicacionDto edit(UUID id, CreatePublicacionDto publicacion, MultipartFile file, Usuario usuario) {
        Optional<Publicacion> publicacionBuscada = repository.findById(id);

        fileSystemStorageService.deleteFile(publicacionBuscada.get().getFichero());
        String filename = storageService.store(file);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(filename)
                .toUriString();
        if (publicacionBuscada.isEmpty())
            return null;
        else {
            GetPublicacionDto publicacion1=  GetPublicacionDto.builder()
                    .id(publicacionBuscada.get().getId())
                    .titulo(publicacion.getTitulo())
                    .texto(publicacion.getTexto())
                    .usernameUsuario(usuario.getUsername())
                    .fichero(uri)
                    .privacidad(Privacidad.PUBLICO.toString())
                    .build();

            repository.save(publicacionDtoConverter.convertPublicacionDtoToPublicacion(publicacion1, publicacionBuscada.get()));
            return publicacion1;
        }
    }

    @Override
    public void deleteById(UUID uuid) {
        repository.deleteById(uuid);
    }


    @Override
    public Optional<Usuario> findByUsername(String username) {
        return usuarioRepository.findAllByUsername(username);
    }


    @Override
    public Optional<Publicacion> findById (UUID id) { return repository.findById(id);}

    @Override
    public Publicacion findById2(UUID id) {
        Optional<Publicacion> publicacion = repository.findById(id);

        if (publicacion.isEmpty()){
            return null;
        }else
            return publicacion.get();

    }
}
