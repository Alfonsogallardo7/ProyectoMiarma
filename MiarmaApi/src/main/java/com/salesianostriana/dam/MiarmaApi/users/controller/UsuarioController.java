package com.salesianostriana.dam.MiarmaApi.users.controller;

import com.salesianostriana.dam.MiarmaApi.users.dto.CreateUsuarioDto;
import com.salesianostriana.dam.MiarmaApi.users.dto.GetUsuarioDto;
import com.salesianostriana.dam.MiarmaApi.users.dto.UsuarioDtoConvert;
import com.salesianostriana.dam.MiarmaApi.users.models.Usuario;
import com.salesianostriana.dam.MiarmaApi.users.services.UsuarioServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioServices usuarioServices;
    private final UsuarioDtoConvert usuarioDtoConvert;

    @PostMapping("/auth/register")
    public ResponseEntity<GetUsuarioDto> addPropietario (@RequestBody CreateUsuarioDto nuevoUsuario) {
        Usuario usuario = usuarioServices.savePropietario(nuevoUsuario);

        if (usuario == null) {
            return ResponseEntity.badRequest().build();
        } else
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDtoConvert.converterUsuarioToUsuarioDto(usuario));
    }
}
