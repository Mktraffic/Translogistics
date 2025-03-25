package com.translogistics.translogistics.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.translogistics.translogistics.dto.RolDTO;
import com.translogistics.translogistics.model.Rol;

@Mapper(componentModel = "spring")
public interface RolMapper {

    @Mapping(target = "tipoRol", expression = "java(obtenerTipoRol(rol))")
    RolDTO toDTO(Rol rol, String licencia, int experiencia);

    Rol toEntity(RolDTO dto);
}
