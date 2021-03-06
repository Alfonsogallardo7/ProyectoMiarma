package com.salesianostriana.dam.MiarmaApi.users.services;

import com.salesianostriana.dam.MiarmaApi.models.Privacidad;
import com.salesianostriana.dam.MiarmaApi.services.StorageService;
import com.salesianostriana.dam.MiarmaApi.services.base.BaseService;
import com.salesianostriana.dam.MiarmaApi.users.dto.CreateUsuarioDto;
import com.salesianostriana.dam.MiarmaApi.users.models.UserRole;
import com.salesianostriana.dam.MiarmaApi.users.models.Usuario;
import com.salesianostriana.dam.MiarmaApi.users.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioServices extends BaseService<Usuario, UUID, UsuarioRepository> implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final StorageService storageService;
    private final UsuarioRepository usuarioRepository;

    public Usuario save (CreateUsuarioDto nuevoUsuario, MultipartFile file) throws IOException {
        String filename = storageService.store(file);

        String extension = StringUtils.getFilenameExtension(filename);
        BufferedImage originalImage = ImageIO.read(file.getInputStream());

        BufferedImage ecaledImage = storageService.simpleResizer(originalImage, 128);

        OutputStream outputStream = Files.newOutputStream(storageService.load(filename));

        ImageIO.write(ecaledImage,extension,outputStream);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(filename)
                .toUriString();

        if (nuevoUsuario.getPassword().contentEquals(nuevoUsuario.getPassword2())){
            Usuario usuario = Usuario.builder()
                    .password(passwordEncoder.encode(nuevoUsuario.getPassword()))
                    .nombre(nuevoUsuario.getNombre())
                    .apellidos(nuevoUsuario.getApellidos())
                    .fotoPerfil(uri)
                    .email(nuevoUsuario.getEmail())
                    .fechaNacimiento(nuevoUsuario.getFechaNacimiento())
                    .username(nuevoUsuario.getUsername())
                    .privacidad(Privacidad.PUBLICO)
                    .role(UserRole.USUARIO)
                    .build();
            return repositorio.save(usuario);
        } else

        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.repositorio.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email + "no encontrado"));
    }
}
