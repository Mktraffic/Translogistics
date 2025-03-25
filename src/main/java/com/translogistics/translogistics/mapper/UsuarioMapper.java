package com.translogistics.translogistics.mapper;

import org.mapstruct.Mapper;

import com.translogistics.translogistics.dto.UsuarioDTO;
import com.translogistics.translogistics.model.Usuario;

@Mapper(componentModel = "spring", uses = {RolMapper.class, PersonaMapper.class})
public interface UsuarioMapper {

    UsuarioDTO toDTO(Usuario usuario);
    Usuario toEntity(UsuarioDTO usuarioDTO);
    
}
