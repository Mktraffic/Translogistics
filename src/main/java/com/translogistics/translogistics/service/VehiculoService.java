package com.translogistics.translogistics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.translogistics.translogistics.dto.VehiculoDTO;
import com.translogistics.translogistics.mapper.VehiculoMapper;
import com.translogistics.translogistics.model.Vehiculo;
import com.translogistics.translogistics.repository.VehiculoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehiculoService {
    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private VehiculoMapper vehiculoMapper;

    public List<VehiculoDTO> findAllVehiculos() {
        List<Vehiculo> vehiculos = vehiculoRepository.findAll();
        return vehiculos.stream()
                .map(vehiculoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public VehiculoDTO addVehiculoInDB(VehiculoDTO vehiculoDTO) {
        Vehiculo vehiculoGuardado = vehiculoRepository.save(vehiculoMapper.toEntity(vehiculoDTO));
        return vehiculoMapper.toDTO(vehiculoGuardado);
    }

    public VehiculoDTO fetchVehiculoByPlaca(String placa) {
        Optional<Vehiculo> vehiculo = vehiculoRepository.findById(placa);
        if (vehiculo.isEmpty()) {
            return null; 
        }
        return vehiculoMapper.toDTO(vehiculo.get());
    }
}