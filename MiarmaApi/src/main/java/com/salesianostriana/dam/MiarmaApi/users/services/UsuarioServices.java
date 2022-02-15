package com.salesianostriana.dam.MiarmaApi.users.services;

import com.salesianostriana.dam.MiarmaApi.models.Privacidad;
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

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioServices extends BaseService<Usuario, UUID, UsuarioRepository> implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    public Usuario save (CreateUsuarioDto nuevoUsuario) {
        if (nuevoUsuario.getPassword().contentEquals(nuevoUsuario.getPassword2())){
            Usuario usuario = Usuario.builder()
                    .password(passwordEncoder.encode(nuevoUsuario.getPassword()))
                    .nombre(nuevoUsuario.getNombre())
                    .apellidos(nuevoUsuario.getApellidos())
                    .fotoPerfil(nuevoUsuario.getFotoPerfil())
                    .email(nuevoUsuario.getEmail())
                    .fechaNacimiento(nuevoUsuario.getFechaNacimiento())
                    .username(nuevoUsuario.getUsername())
                    .privacidad(Privacidad.PUBLICO)
                    .role(UserRole.USUARIO)
                    .build();
            return save(usuario);
        } else

        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.repositorio.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email + "no encontrado"));
    }
}
