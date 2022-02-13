package com.salesianostriana.dam.MiarmaApi.users.repository;

import com.salesianostriana.dam.MiarmaApi.users.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
}
