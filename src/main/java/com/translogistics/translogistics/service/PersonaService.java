package com.translogistics.translogistics.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.translogistics.translogistics.dto.PersonaDTO;
import com.translogistics.translogistics.mapper.PersonaMapper;
import com.translogistics.translogistics.model.Persona;
import com.translogistics.translogistics.repository.PersonaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private PersonaMapper personaMapper;

    // Obtener todas las personas
    public List<PersonaDTO> findAllPersonas() {
        List<Persona> personas = personaRepository.findAll();
        return personas.stream()
                .map(personaMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Guardar una persona en la base de datos
    public PersonaDTO addPersonaInDB(PersonaDTO personaDTO) {
        Persona personaGuardada = personaRepository.save(personaMapper.toEntity(personaDTO));
        return personaMapper.toDTO(personaGuardada);
    }

    // Obtener persona por ID
    public ResponseEntity<PersonaDTO> fetchPersonaById(Long id) {
        Optional<Persona> persona = personaRepository.findById(id);
        if (persona.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(personaMapper.toDTO(persona.get()), HttpStatus.OK);
    }

}
