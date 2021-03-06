package com.salesianostriana.dam.MiarmaApi.repository;

import com.salesianostriana.dam.MiarmaApi.models.Privacidad;
import com.salesianostriana.dam.MiarmaApi.models.Publicacion;
import com.salesianostriana.dam.MiarmaApi.users.models.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PublicacionesRepository extends JpaRepository<Publicacion, UUID> {

    Optional<Page<Publicacion>> findAllByPrivacidad(Privacidad privacidad, Pageable pageable);



}
