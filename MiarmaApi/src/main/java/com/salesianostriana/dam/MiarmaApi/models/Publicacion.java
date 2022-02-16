package com.salesianostriana.dam.MiarmaApi.models;

import com.salesianostriana.dam.MiarmaApi.users.models.Usuario;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Builder
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Publicacion implements Serializable {

    @GeneratedValue(generator = "UUID")
    @Id
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )}
    )
    private UUID id;

    private String titulo;

    private String texto;

    private String fichero;

    @Enumerated(EnumType.STRING)
    private Privacidad privacidad;

    @ManyToOne
    private Usuario usuario;
}
