package com.translogistics.translogistics.mapper;

import org.springframework.stereotype.Component;

import com.translogistics.translogistics.dto.PersonaDTO;
import com.translogistics.translogistics.model.Persona;

@Component
public class PersonaMapperImplement implements PersonaMapper {

    @Override
    public PersonaDTO toDTO(Persona persona) {
        if (!persona.equals(null)) {
            return new PersonaDTO(persona.getId(), persona.getNombre(), persona.getApellido());
        }
        return null;
    }

    @Override
    public Persona toEntity(PersonaDTO personaDTO) {
        if (!personaDTO.equals(null)) {
            return new Persona(personaDTO.getId(), personaDTO.getNombre(), personaDTO.getApellido());
        }
        return null;
    }

}
