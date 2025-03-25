package com.translogistics;

import com.translogistics.translogistics.dto.PersonaDTO;
import com.translogistics.translogistics.dto.RolAdministradorDTO;
import com.translogistics.translogistics.dto.RolConductorDTO;
import com.translogistics.translogistics.dto.RolDTO;
import com.translogistics.translogistics.dto.RolDespachadorDTO;
import com.translogistics.translogistics.dto.UsuarioDTO;
import com.translogistics.translogistics.mapper.UsuarioMapper;
import com.translogistics.translogistics.model.Usuario;
import com.translogistics.translogistics.model.Persona;
import com.translogistics.translogistics.model.Rol;
import com.translogistics.translogistics.model.RolAdministrador;
import com.translogistics.translogistics.model.RolConductor;
import com.translogistics.translogistics.model.RolDespachador;
import com.translogistics.translogistics.repository.UsuarioRepository;
import com.translogistics.translogistics.service.UsuarioService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioMapper usuarioMapper;

    @InjectMocks
    private UsuarioService usuarioService;
    
   
    @Test
    void testFindAllUsuarios() {
        // Arrange
    Rol rolAdm = new RolAdministrador(1L, "ADMINISTRADOR");
    Rol rolConductor = new RolConductor(2L, "CONDUCTOR", "licencia", 2);
    Rol rolDespachador = new RolDespachador(3L, "DESPACHADOR");
    Persona persona1 = new Persona(1L, "juan", "perez");
    Persona persona2 = new Persona(2L, "luis", "rincon");
    Persona persona3 = new Persona(3L, "pedro", "gomez");
    Usuario usuario1 = new Usuario(1L, "user1", "password1", rolAdm, persona1);
    Usuario usuario2 = new Usuario(2L, "user2", "password2", rolConductor, persona2);
    Usuario usuario3 = new Usuario(3L, "user3", "password3", rolDespachador, persona3);

    List<Usuario> usuarios = Arrays.asList(usuario1, usuario2, usuario3);

    PersonaDTO personaDTO1 = new PersonaDTO(1L, "juan", "perez");
    PersonaDTO personaDTO2 = new PersonaDTO(2L, "luis", "rincon");
    PersonaDTO personaDTO3 = new PersonaDTO(3L, "pedro", "gomez");
    RolDTO rolDTO1 = new RolAdministradorDTO(1L, "ADMINISTRADOR");
    RolDTO rolDTO2 = new RolConductorDTO(2L, "CONDUCTOR", "licencia", 2);
    RolDTO rolDTO3 = new RolDespachadorDTO(3L, "DESPACHADOR");
    UsuarioDTO usuarioDTO1 = new UsuarioDTO(1L, "user1", "password1", rolDTO1, personaDTO1);
    UsuarioDTO usuarioDTO2 = new UsuarioDTO(2L, "user2", "password2", rolDTO2, personaDTO2);
    UsuarioDTO usuarioDTO3 = new UsuarioDTO(3L, "user3", "password3", rolDTO3, personaDTO3);

    List<UsuarioDTO> expected = Arrays.asList(usuarioDTO1, usuarioDTO2, usuarioDTO3);

    // Configurar mocks
    Mockito.when(usuarioRepository.findAll()).thenReturn(usuarios);
    Mockito.when(usuarioMapper.toDTO(usuario1)).thenReturn(usuarioDTO1);
    Mockito.when(usuarioMapper.toDTO(usuario2)).thenReturn(usuarioDTO2);
    Mockito.when(usuarioMapper.toDTO(usuario3)).thenReturn(usuarioDTO3);

    // Act
    List<UsuarioDTO> result = usuarioService.findAllUsuarios();

    // Assert
    Assertions.assertEquals(expected.size(), result.size());
    Assertions.assertEquals(expected.get(0).getId(), result.get(0).getId());
    Assertions.assertEquals(expected.get(1).getId(), result.get(1).getId());
    Assertions.assertEquals(expected.get(2).getId(), result.get(2).getId());
    Mockito.verify(usuarioRepository).findAll();
    }

    @Test
    void testAddUsuarioInDB() {
        
    Rol rolAdm = new RolAdministrador(1L, "ADMINISTRADOR");
    Rol rolConductor = new RolConductor(2L, "CONDUCTOR", "licencia", 2);
    Rol rolDespachador = new RolDespachador(3L, "DESPACHADOR");
    Persona persona1 = new Persona(1L, "juan", "perez");
    Persona persona2 = new Persona(2L, "luis", "rincon");
    Persona persona3 = new Persona(3L, "pedro", "gomez");
    Usuario usuario1 = new Usuario(1L, "user1", "password1", rolAdm, persona1);
    Usuario usuario2 = new Usuario(2L, "user2", "password2", rolConductor, persona2);
    Usuario usuario3 = new Usuario(3L, "user3", "password3", rolDespachador, persona3);

    PersonaDTO personaDTO1 = new PersonaDTO(1L, "juan", "perez");
    PersonaDTO personaDTO2 = new PersonaDTO(2L, "luis", "rincon");
    PersonaDTO personaDTO3 = new PersonaDTO(3L, "pedro", "gomez");
    RolDTO rolDTO1 = new RolAdministradorDTO(1L, "ADMINISTRADOR");
    RolDTO rolDTO2 = new RolConductorDTO(2L, "CONDUCTOR", "licencia", 2);
    RolDTO rolDTO3 = new RolDespachadorDTO(3L, "DESPACHADOR");
    UsuarioDTO usuarioDTO1 = new UsuarioDTO(1L, "user1", "password1", rolDTO1, personaDTO1);
    UsuarioDTO usuarioDTO2 = new UsuarioDTO(2L, "user2", "password2", rolDTO2, personaDTO2);
    UsuarioDTO usuarioDTO3 = new UsuarioDTO(3L, "user3", "password3", rolDTO3, personaDTO3);

    
    Mockito.when(usuarioMapper.toEntity(usuarioDTO1)).thenReturn(usuario1);
    Mockito.when(usuarioRepository.save(usuario1)).thenReturn(usuario1);
    Mockito.when(usuarioMapper.toDTO(usuario1)).thenReturn(usuarioDTO1);

    Mockito.when(usuarioMapper.toEntity(usuarioDTO2)).thenReturn(usuario2);
    Mockito.when(usuarioRepository.save(usuario2)).thenReturn(usuario2);
    Mockito.when(usuarioMapper.toDTO(usuario2)).thenReturn(usuarioDTO2);

   
    Mockito.when(usuarioMapper.toEntity(usuarioDTO3)).thenReturn(usuario3);
    Mockito.when(usuarioRepository.save(usuario3)).thenReturn(usuario3);
    Mockito.when(usuarioMapper.toDTO(usuario3)).thenReturn(usuarioDTO3);

   
    UsuarioDTO result1 = usuarioService.addUsuarioInDB(usuarioDTO1);
    Assertions.assertNotNull(result1);
    Assertions.assertEquals(usuarioDTO1.getUser_name(), result1.getUser_name());
    Mockito.verify(usuarioRepository).save(usuario1);

    
    UsuarioDTO result2 = usuarioService.addUsuarioInDB(usuarioDTO2);
    Assertions.assertNotNull(result2);
    Assertions.assertEquals(usuarioDTO2.getUser_name(), result2.getUser_name());
    Mockito.verify(usuarioRepository).save(usuario2);

    UsuarioDTO result3 = usuarioService.addUsuarioInDB(usuarioDTO3);
    Assertions.assertNotNull(result3);
    Assertions.assertEquals(usuarioDTO3.getUser_name(), result3.getUser_name());
    Mockito.verify(usuarioRepository).save(usuario3);
    }

    @Test
    void testValidateUserByUserName() {
      
    Rol rolAdm = new RolAdministrador(1L, "ADMINISTRADOR");
    Rol rolConductor = new RolConductor(2L, "CONDUCTOR", "licencia", 2);
    Rol rolDespachador = new RolDespachador(3L, "DESPACHADOR");
    Persona persona1 = new Persona(1L, "juan", "perez");
    Persona persona2 = new Persona(2L, "luis", "rincon");
    Persona persona3 = new Persona(3L, "pedro", "gomez");
    Usuario usuario1 = new Usuario(1L, "user1", "password1", rolAdm, persona1);
    Usuario usuario2 = new Usuario(2L, "user2", "password2", rolConductor, persona2);
    Usuario usuario3 = new Usuario(3L, "user3", "password3", rolDespachador, persona3);

    PersonaDTO personaDTO1 = new PersonaDTO(1L, "juan", "perez");
    PersonaDTO personaDTO2 = new PersonaDTO(2L, "luis", "rincon");
    PersonaDTO personaDTO3 = new PersonaDTO(3L, "pedro", "gomez");
    RolDTO rolDTO1 = new RolAdministradorDTO(1L, "ADMINISTRADOR");
    RolDTO rolDTO2 = new RolConductorDTO(2L, "CONDUCTOR", "licencia", 2);
    RolDTO rolDTO3 = new RolDespachadorDTO(3L, "DESPACHADOR");
    UsuarioDTO usuarioDTO1 = new UsuarioDTO(1L, "user1", "password1", rolDTO1, personaDTO1);
    UsuarioDTO usuarioDTO2 = new UsuarioDTO(2L, "user2", "password2", rolDTO2, personaDTO2);
    UsuarioDTO usuarioDTO3 = new UsuarioDTO(3L, "user3", "password3", rolDTO3, personaDTO3);

    
    Mockito.when(usuarioRepository.findAll()).thenReturn(Arrays.asList(usuario1, usuario2, usuario3));
    Mockito.when(usuarioMapper.toDTO(usuario1)).thenReturn(usuarioDTO1);
    Mockito.when(usuarioMapper.toDTO(usuario2)).thenReturn(usuarioDTO2);
    Mockito.when(usuarioMapper.toDTO(usuario3)).thenReturn(usuarioDTO3);

    String result1 = usuarioService.validateUserByUserName("user1", "password1");
    Assertions.assertEquals("true,ADMINISTRADOR", result1);

  
    String result2 = usuarioService.validateUserByUserName("user2", "password2");
    Assertions.assertEquals("true,CONDUCTOR", result2);

   
    String result3 = usuarioService.validateUserByUserName("user3", "password3");
    Assertions.assertEquals("true,DESPACHADOR", result3);

    String result4 = usuarioService.validateUserByUserName("user4", "password4");
    Assertions.assertEquals("false,", result4);
    }
}