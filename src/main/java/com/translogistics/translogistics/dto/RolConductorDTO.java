package com.translogistics.translogistics.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolConductorDTO extends RolDTO {
    private String licencia;
    private int experiencia;

    public RolConductorDTO(Long id, String nombreRol, String licencia, int experiencia) {
        super(id, nombreRol);
        this.licencia = licencia;
        this.experiencia = experiencia;
    }

    @JsonProperty("tipo")
    public String getTipo() {
        return "conductor";
    }
}
