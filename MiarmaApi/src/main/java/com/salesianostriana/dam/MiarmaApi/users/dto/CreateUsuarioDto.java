package com.salesianostriana.dam.MiarmaApi.users.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@SuperBuilder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CreateUsuarioDto {

    private String nombre, apellidos, username, email, password, password2, fotoPerfil;

    private LocalDate fechaNacimiento;
}
