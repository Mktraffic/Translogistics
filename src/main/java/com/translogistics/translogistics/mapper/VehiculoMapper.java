package com.translogistics.translogistics.mapper;

import org.mapstruct.Mapper;

import com.translogistics.translogistics.dto.VehiculoDTO;
import com.translogistics.translogistics.model.Vehiculo;

@Mapper(componentModel = "spring")
public interface VehiculoMapper {

    VehiculoDTO toDTO(Vehiculo entity);
    Vehiculo toEntity(VehiculoDTO dto);
}
