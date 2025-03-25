package com.translogistics.translogistics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.translogistics.translogistics.model.Rol;
import com.translogistics.translogistics.model.RolAdministrador;
import com.translogistics.translogistics.model.RolConductor;
import com.translogistics.translogistics.model.RolDespachador;
import com.translogistics.translogistics.repository.RolRepository;
import com.translogistics.translogistics.service.RolService;

@ExtendWith(MockitoExtension.class)
class RolServiceTest {

    @Mock
    private RolRepository rolRepository;

    @InjectMocks
    private RolService rolService;

    @Test
    void testGuardarRol() {
        
        Rol rol = new RolAdministrador(1L, "ADMINISTRADOR");
        Mockito.when(rolRepository.save(rol)).thenReturn(rol);

    
        Rol result = rolService.guardarRol(rol);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(rol.getId(), result.getId());
        Assertions.assertEquals(rol.getNombreRol(), result.getNombreRol());
        Mockito.verify(rolRepository).save(rol);
    }

    @Test
    void testGuardarRolConductor() {
        
        RolConductor rolConductor = new RolConductor(2L, "CONDUCTOR", "licencia", 5);
        Mockito.when(rolRepository.save(rolConductor)).thenReturn(rolConductor);

        RolConductor result = rolService.guardarRolConductor(rolConductor);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(rolConductor.getId(), result.getId());
        Assertions.assertEquals(rolConductor.getNombreRol(), result.getNombreRol());
        Assertions.assertEquals(rolConductor.getLicencia(), result.getLicencia());
        Assertions.assertEquals(rolConductor.getExperiencia(), result.getExperiencia());
        Mockito.verify(rolRepository).save(rolConductor);
    }

    @Test
    void testGuardarRolDespachador() {
        
        RolDespachador rolDespachador = new RolDespachador(3L, "DESPACHADOR");
        Mockito.when(rolRepository.save(rolDespachador)).thenReturn(rolDespachador);

     
        RolDespachador result = rolService.guardarRolDespachador(rolDespachador);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(rolDespachador.getId(), result.getId());
        Assertions.assertEquals(rolDespachador.getNombreRol(), result.getNombreRol());
        Mockito.verify(rolRepository).save(rolDespachador);
    }

    @Test
    void testGuardarRolAdministrador() {
       
        RolAdministrador rolAdministrador = new RolAdministrador(4L, "ADMINISTRADOR");
        Mockito.when(rolRepository.save(rolAdministrador)).thenReturn(rolAdministrador);
        RolAdministrador result = rolService.guardarRolAdministrador(rolAdministrador);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(rolAdministrador.getId(), result.getId());
        Assertions.assertEquals(rolAdministrador.getNombreRol(), result.getNombreRol());
        Mockito.verify(rolRepository).save(rolAdministrador);
    }
}