package com.translogistics.translogistics.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "tipo")
@JsonSubTypes({
        @JsonSubTypes.Type(value = RolConductorDTO.class, name = "conductor"),
        @JsonSubTypes.Type(value = RolAdministradorDTO.class, name = "administrador"),
        @JsonSubTypes.Type(value = RolDespachadorDTO.class, name = "despachador")
})
public abstract class RolDTO {
    private Long id;
    private String nombreRol;

}
