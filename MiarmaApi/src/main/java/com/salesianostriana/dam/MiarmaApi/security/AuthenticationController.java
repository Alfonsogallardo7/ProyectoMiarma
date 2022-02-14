package com.salesianostriana.dam.MiarmaApi.security;

import com.salesianostriana.dam.MiarmaApi.security.dto.LoginDto;
import com.salesianostriana.dam.MiarmaApi.security.jwt.JwtProvider;
import com.salesianostriana.dam.MiarmaApi.security.jwt.JwtUserResponse;
import com.salesianostriana.dam.MiarmaApi.users.models.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager manager;

    private final JwtProvider jwtProvider;

    @PostMapping("/auth/login")
    public ResponseEntity<?> login (@RequestBody LoginDto loginDto) {
        Authentication authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        Usuario usuario = (Usuario) authentication.getPrincipal();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(convertUserToJwtUserResponse(usuario, jwt));
    }

    @GetMapping("/me")
    public ResponseEntity<?> me (@AuthenticationPrincipal Usuario usuario) {
        return ResponseEntity.ok(convertUserToJwtUserResponse(usuario, null));
    }

    private JwtUserResponse convertUserToJwtUserResponse (Usuario usuario, String jwt) {
        return JwtUserResponse.builder()
                .nombre(usuario.getNombre())
                .username(usuario.getUsername())
                .apellidos(usuario.getApellidos())
                .email(usuario.getEmail())
                .fotoPerfil(usuario.getFotoPerfil())
                .privacidad(usuario.isPrivacidad())
                .rol(usuario.getRole().name())
                .token(jwt)
                .build();
    }
}
