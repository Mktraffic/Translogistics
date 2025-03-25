package com.translogistics.translogistics.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue("ADMINISTRADOR")
public class RolAdministrador extends Rol {
    public RolAdministrador(Long id, String nombreRol) {
        super(id, nombreRol);
    }
}
