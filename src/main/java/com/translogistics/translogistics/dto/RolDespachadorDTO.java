package com.translogistics.translogistics.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RolDespachadorDTO extends RolDTO {
    public RolDespachadorDTO(Long id, String nombreRol) {
        super(id, nombreRol);
    }

    @JsonProperty("tipo")
    public String getTipo() {
        return "despachador";
    }
}

