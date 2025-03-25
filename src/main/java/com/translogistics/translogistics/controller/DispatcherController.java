package com.translogistics.translogistics.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.translogistics.translogistics.dto.RegistroViajeDTO;
import com.translogistics.translogistics.dto.UsuarioDTO;
import com.translogistics.translogistics.dto.VehiculoDTO;
import com.translogistics.translogistics.service.RegistroViajeService;
import com.translogistics.translogistics.service.UsuarioService;
import com.translogistics.translogistics.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class DispatcherController {
    @Autowired
    private VehiculoService vehiculoService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private RegistroViajeService registroViajeService;

    @PostMapping("/asignar")
    public ResponseEntity<RegistroViajeDTO> procesarAsignacion(@ModelAttribute("vehiculoPlaca") String placaVehiculo,
                                                               @ModelAttribute("conductorNombre") String nombreConductor) {

        ResponseEntity<VehiculoDTO> vehiculoResponse = vehiculoService.fetchVehiculoByPlaca(placaVehiculo);
        if (vehiculoResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        VehiculoDTO vehiculoDTO = vehiculoResponse.getBody();

        // Buscar el conductor por nombre
        List<UsuarioDTO> response = usuarioService.findAllUsuarios();
        UsuarioDTO conductor = null;
        for (UsuarioDTO usuarioDTO : response) {
            String name = usuarioDTO.getPersona().getNombre() + " " + usuarioDTO.getPersona().getApellido();
            if (nombreConductor.equals(name) && usuarioDTO.getRol().getNombreRol().equals("CONDUCTOR")) {
                conductor = usuarioDTO;
                break;
            }
        }

        if (conductor == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        // Crear el registro de viaje
        RegistroViajeDTO registroViajeDTO = new RegistroViajeDTO();
        registroViajeDTO.setFechaViaje(LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        registroViajeDTO.setVehiculo(vehiculoDTO);
        registroViajeDTO.setConductor(conductor);

        // Guardar el registro de viaje en la base de datos
        RegistroViajeDTO savedRegistroViajeDTO = registroViajeService.addRegistroViaje(registroViajeDTO);

        return new ResponseEntity<>(savedRegistroViajeDTO, HttpStatus.CREATED);
    }

    @GetMapping("/dispatcher/asignarConductor")
    public String asignarConductor(Model model) {
        model.addAttribute("vehiculos", findAllPlatesVehicles());
        model.addAttribute("conductores", findAllDrivers());
        return "assignDriver";
    }


    @GetMapping("/all/drivers")
    public List<String> findAllDrivers() {
        List<UsuarioDTO> usuarios = usuarioService.findAllUsuarios();
        List<String> drivers = new ArrayList<>();
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getRol().getNombreRol().equals("CONDUCTOR")) {
                drivers.add(
                        usuarios.get(i).getPersona().getNombre() + " " + usuarios.get(i).getPersona().getApellido());
            }
        }
        return drivers;
    }
    @GetMapping("/all/vehiculo")
    public List<String> findAllPlatesVehicles() {
        List<VehiculoDTO> vehiculos = vehiculoService.findAllVehiculos();
        List<String> plates = new ArrayList<>();
        for (int i = 0; i < vehiculos.size(); i++) {
            plates.add(vehiculos.get(i).getPlaca() + " - " + vehiculos.get(i).getTipo());
        }
        return plates;
    }
}
