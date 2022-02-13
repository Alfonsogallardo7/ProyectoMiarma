package com.salesianostriana.dam.MiarmaApi.users.services;

import com.salesianostriana.dam.MiarmaApi.services.base.BaseService;
import com.salesianostriana.dam.MiarmaApi.users.models.Usuario;
import com.salesianostriana.dam.MiarmaApi.users.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioServices extends BaseService<Usuario, UUID, UsuarioRepository> {
}
