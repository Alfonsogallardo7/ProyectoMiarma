package com.salesianostriana.dam.MiarmaApi.repository;

import com.salesianostriana.dam.MiarmaApi.models.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PublicacionesRepository extends JpaRepository<Publicacion, UUID> {
}
