package com.translogistics.translogistics.mapper;

import org.springframework.stereotype.Component;

import com.translogistics.translogistics.dto.RolAdministradorDTO;
import com.translogistics.translogistics.dto.RolConductorDTO;
import com.translogistics.translogistics.dto.RolDTO;
import com.translogistics.translogistics.dto.RolDespachadorDTO;
import com.translogistics.translogistics.model.Rol;
import com.translogistics.translogistics.model.RolAdministrador;
import com.translogistics.translogistics.model.RolConductor;
import com.translogistics.translogistics.model.RolDespachador;

@Component
public class RolMapperImplement implements RolMapper {

    @Override
    public RolDTO toDTO(Rol rol, String licencia, int experiencia) {
        if (rol.getNombreRol().equals("ADMINISTRADOR")) {
            return new RolAdministradorDTO(rol.getId(), "ADMINISTRADOR");
        }
        if (rol.getNombreRol().equals("CONDUCTOR")) {
            return new RolConductorDTO(rol.getId(), "CONDUCTOR", licencia, experiencia);
        }
        if (rol.getNombreRol().equals("DESPACHADOR")) {
            return new RolDespachadorDTO(rol.getId(), "DESPACHADOR");
        }
        return null;
    }

    @Override
    public Rol toEntity(RolDTO dto) {
        if (dto instanceof RolAdministradorDTO) {
            return new RolAdministrador(dto.getId(), "ADMINSTRADOR");
        }
        if (dto instanceof RolConductorDTO) {
            RolConductorDTO conductorDTO = (RolConductorDTO) dto;
            return new RolConductor(conductorDTO.getId(), "CONDUCTOR", conductorDTO.getLicencia(), conductorDTO.getExperiencia());
        }
        if (dto instanceof RolDespachadorDTO) {
            return new RolDespachador(dto.getId(), "DESPACHADOR");
        }
        return null;
    }

}