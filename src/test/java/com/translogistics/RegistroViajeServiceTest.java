package com.translogistics;

import com.translogistics.translogistics.dto.RegistroViajeDTO;
import com.translogistics.translogistics.dto.UsuarioDTO;
import com.translogistics.translogistics.dto.VehiculoDTO;
import com.translogistics.translogistics.mapper.RegistroViajeMapper;
import com.translogistics.translogistics.model.RegistroViaje;
import com.translogistics.translogistics.model.Usuario;
import com.translogistics.translogistics.model.Vehiculo;
import com.translogistics.translogistics.repository.RegistroViajeRepository;
import com.translogistics.translogistics.service.RegistroViajeService;

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
class RegistroViajeServiceTest {

    @Mock
    private RegistroViajeRepository registroViajeRepository;

    @Mock
    private RegistroViajeMapper registroViajeMapper;

    @InjectMocks
    private RegistroViajeService registroViajeService;

    @Test
    void testFindAllRegistroViaje() {
        // Arrange
        Vehiculo vehiculo1 = new Vehiculo("ABC123", "modelo",2020 ,"bus", "activo");
        Usuario conductor1 = new Usuario(1L, "user1", "password1", null, null);
        RegistroViaje registro1 = new RegistroViaje(1L, "2025-03-24", vehiculo1, conductor1);
        Vehiculo vehiculo2 = new Vehiculo("XYZ789", "modelo",2021 ,"carga", "activo");
        Usuario conductor2 = new Usuario(2L, "user2", "password2", null, null);
        RegistroViaje registro2 = new RegistroViaje(2L, "2025-03-25", vehiculo2, conductor2);

        List<RegistroViaje> registros = Arrays.asList(registro1, registro2);

        VehiculoDTO vehiculoDTO1 = new VehiculoDTO("ABC123", "modelo", 2020,"bus", "activo");
        UsuarioDTO conductorDTO1 = new UsuarioDTO(1L, "user1", "password1", null, null);
        RegistroViajeDTO registroDTO1 = new RegistroViajeDTO(1L, "2025-03-24", vehiculoDTO1, conductorDTO1);
        VehiculoDTO vehiculoDTO2 = new VehiculoDTO("XYZ789", "modelo", 2021,"carga", "activo");
        UsuarioDTO conductorDTO2 = new UsuarioDTO(2L, "user2", "password2", null, null);
        RegistroViajeDTO registroDTO2 = new RegistroViajeDTO(2L, "2025-03-25", vehiculoDTO2, conductorDTO2);

        List<RegistroViajeDTO> expected = Arrays.asList(registroDTO1, registroDTO2);

        Mockito.when(registroViajeRepository.findAll()).thenReturn(registros);
        Mockito.when(registroViajeMapper.toDTO(registro1)).thenReturn(registroDTO1);
        Mockito.when(registroViajeMapper.toDTO(registro2)).thenReturn(registroDTO2);

     
        List<RegistroViajeDTO> result = registroViajeService.findAllRegistroViaje();

       
        Assertions.assertEquals(expected.size(), result.size());
        Assertions.assertEquals(expected.get(0).getId(), result.get(0).getId());
        Assertions.assertEquals(expected.get(1).getId(), result.get(1).getId());
        Mockito.verify(registroViajeRepository).findAll();
    }

    @Test
    void testAddRegistroViaje() {
        // Arrange
        Vehiculo vehiculo = new Vehiculo("ABC123", "modelo",2020 ,"bus", "activo");
        Usuario conductor = new Usuario(1L, "user1", "password1", null, null);
        RegistroViaje registro = new RegistroViaje(1L, "2025-03-24", vehiculo, conductor);

        VehiculoDTO vehiculoDTO = new VehiculoDTO("ABC123", "modelo", 2020,"bus", "activo");
        UsuarioDTO conductorDTO = new UsuarioDTO(1L, "user1", "password1", null, null);
        RegistroViajeDTO registroDTO = new RegistroViajeDTO(1L, "2025-03-24", vehiculoDTO, conductorDTO);

        Mockito.when(registroViajeMapper.toEntity(registroDTO)).thenReturn(registro);
        Mockito.when(registroViajeRepository.save(registro)).thenReturn(registro);
        Mockito.when(registroViajeMapper.toDTO(registro)).thenReturn(registroDTO);

    
        RegistroViajeDTO result = registroViajeService.addRegistroViaje(registroDTO);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(registroDTO.getId(), result.getId());
        Assertions.assertEquals(registroDTO.getFechaViaje(), result.getFechaViaje());
        Assertions.assertEquals(registroDTO.getVehiculo().getPlaca(), result.getVehiculo().getPlaca());
        Assertions.assertEquals(registroDTO.getConductor().getUser_name(), result.getConductor().getUser_name());
        Mockito.verify(registroViajeRepository).save(registro);
    }
}
