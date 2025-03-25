package com.translogistics.translogistics.mapper;

import com.translogistics.translogistics.dto.PersonaDTO;
import com.translogistics.translogistics.model.Persona;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface PersonaMapper {

    PersonaDTO toDTO(Persona persona);
    Persona toEntity(PersonaDTO personaDTO);
}