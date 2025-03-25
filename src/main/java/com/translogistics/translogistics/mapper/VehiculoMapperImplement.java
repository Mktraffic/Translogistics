package com.translogistics.translogistics.mapper;

import com.translogistics.translogistics.dto.VehiculoDTO;
import com.translogistics.translogistics.model.Vehiculo;
import org.springframework.stereotype.Component;
@Component
public class VehiculoMapperImplement {
    
        @Override
        public VehiculoDTO toDTO(Vehiculo entity) {
            return new VehiculoDTO(entity.getPlaca(), entity.getModelo(), entity.getAnio(), entity.getTipo(), entity.getEstado());
        }

        @Override
        public Vehiculo toEntity(VehiculoDTO dto) {
            return new Vehiculo(dto.getPlaca(), dto.getModelo(), dto.getAnio(), dto.getTipo(), dto.getEstado());
        }

    }
