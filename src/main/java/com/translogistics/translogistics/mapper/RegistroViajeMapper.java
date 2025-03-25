package com.translogistics.translogistics.mapper;

import org.mapstruct.Mapper;

import com.translogistics.translogistics.dto.RegistroViajeDTO;
import com.translogistics.translogistics.model.RegistroViaje;

@Mapper(componentModel = "spring")
public interface RegistroViajeMapper {
    RegistroViajeDTO toDTO(RegistroViaje entity);
    RegistroViaje toEntity(RegistroViajeDTO dto);

}
