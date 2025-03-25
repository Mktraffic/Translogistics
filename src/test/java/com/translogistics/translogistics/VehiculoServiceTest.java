package com.translogistics.translogistics;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.translogistics.translogistics.dto.VehiculoDTO;
import com.translogistics.translogistics.mapper.VehiculoMapper;
import com.translogistics.translogistics.model.Vehiculo;
import com.translogistics.translogistics.repository.VehiculoRepository;
import com.translogistics.translogistics.service.VehiculoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class VehiculoServiceTest {

    @Mock
    private VehiculoRepository vehiculoRepository;

    @Mock
    private VehiculoMapper vehiculoMapper;

    @InjectMocks
    private VehiculoService vehiculoService;

    @BeforeEach
    void setUp() {
        VehiculoDTO vehiculoDTO = new VehiculoDTO("ABC123", "mazda", 2020, "bus", "activo");
        VehiculoDTO vehiculoDTO2 = new VehiculoDTO("DEF456", "chevrolet", 2021, "carguero", "activo");
        vehiculoService.addVehiculoInDB(vehiculoDTO);
        vehiculoService.addVehiculoInDB(vehiculoDTO2);

    }

    @Test
    void testFindAllVehiculos() {

        Vehiculo vehiculo1 = new Vehiculo("ABC123", "mazda", 2020, "bus", "activo");
        Vehiculo vehiculo2 = new Vehiculo("DEF456", "chevrolet", 2021, "carguero", "activo");
        List<Vehiculo> vehiculos = Arrays.asList(vehiculo1, vehiculo2);

        VehiculoDTO vehiculoDTO1 = new VehiculoDTO("ABC123", "mazda", 2020, "bus", "activo");
        VehiculoDTO vehiculoDTO2 = new VehiculoDTO("DEF456", "chevrolet", 2021, "carguero", "activo");
        List<VehiculoDTO> expected = Arrays.asList(vehiculoDTO1, vehiculoDTO2);

        Mockito.when(vehiculoRepository.findAll()).thenReturn(vehiculos);
        Mockito.when(vehiculoMapper.toDTO(vehiculo1)).thenReturn(vehiculoDTO1);
        Mockito.when(vehiculoMapper.toDTO(vehiculo2)).thenReturn(vehiculoDTO2);

        List<VehiculoDTO> result = vehiculoService.findAllVehiculos();
        Mockito.verify(vehiculoRepository).findAll();
        Assertions.assertEquals(expected.size(), result.size());
        Assertions.assertEquals(expected.get(0).getPlaca(), result.get(0).getPlaca());
        Assertions.assertEquals(expected.get(1).getPlaca(), result.get(1).getPlaca());
    }
    @Test
    void testAddVehiculoInDB() {
        Vehiculo vehiculo = new Vehiculo("ABC241", "mazda", 2020, "bus", "activo");
        VehiculoDTO vehiculoDTO = new VehiculoDTO("ABC241", "mazda", 2020, "bus", "activo");

        Mockito.when(vehiculoMapper.toEntity(vehiculoDTO)).thenReturn(vehiculo);
        Mockito.when(vehiculoRepository.save(vehiculo)).thenReturn(vehiculo);
        Mockito.when(vehiculoMapper.toDTO(vehiculo)).thenReturn(vehiculoDTO);

        VehiculoDTO resultado = vehiculoService.addVehiculoInDB(vehiculoDTO);
        Assertions.assertEquals(vehiculoDTO.getPlaca(), resultado.getPlaca());
        Mockito.verify(vehiculoRepository).save(vehiculo);
    }

    @Test
    void testFetchVehiculoByPlaca() {

        String placa = "ABC123";
        Vehiculo vehiculo = new Vehiculo("ABC123", "mazda", 2020, "bus", "activo");
        VehiculoDTO vehiculoDTO = new VehiculoDTO("ABC123", "mazda", 2020, "bus", "activo");
        Mockito.when(vehiculoRepository.findById(placa)).thenReturn(Optional.of(vehiculo));
        Mockito.when(vehiculoMapper.toDTO(vehiculo)).thenReturn(vehiculoDTO);

        VehiculoDTO response = vehiculoService.fetchVehiculoByPlaca(placa);

        Assertions.assertEquals(vehiculoDTO.getPlaca(), response.getPlaca());
        Mockito.verify(vehiculoRepository).findById(placa);


    }

}