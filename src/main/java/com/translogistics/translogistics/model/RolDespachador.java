package com.translogistics.translogistics.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue("DESPACHADOR") // Se identifica como un despachador
public class RolDespachador extends Rol {

    public RolDespachador(Long id, String nombreRol) {
        super(id, nombreRol);
    }
}
