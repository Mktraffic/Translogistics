package com.translogistics.translogistics.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("CONDUCTOR")
@Data
@NoArgsConstructor 
public class RolConductor extends Rol {
    
    @Column(name = "licencia", length = 200)
    @Size(min = 1, max = 200)
    private String licencia;

    @Column(name = "anios_experiencia")
    private int experiencia;

    public RolConductor(Long id, String nombreRol, String licencia, int experiencia) {
        super(id, nombreRol);
        this.licencia = licencia;
        this.experiencia = experiencia;
    }
}