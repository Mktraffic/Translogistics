package com.translogistics.translogistics.controller;

import com.translogistics.translogistics.dto.PersonaDTO;
import com.translogistics.translogistics.dto.RolConductorDTO;
import com.translogistics.translogistics.dto.RolDespachadorDTO;
import com.translogistics.translogistics.dto.UsuarioDTO;
import com.translogistics.translogistics.dto.UsuarioRolConductorDTO;
import com.translogistics.translogistics.dto.VehiculoDTO;
import com.translogistics.translogistics.model.RolConductor;
import com.translogistics.translogistics.model.RolDespachador;
import com.translogistics.translogistics.service.PersonaService;
import com.translogistics.translogistics.service.RolService;
import com.translogistics.translogistics.service.UsuarioService;
import com.translogistics.translogistics.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdministratorController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private VehiculoService vehiculoService;
    @Autowired
    private PersonaService personaService;
    @Autowired
    private RolService rolService;

    @PostMapping("/registroConductor")
    public String registrarConductor(@ModelAttribute UsuarioRolConductorDTO usuarioConductor, Model model) {
        if (personaService.findById(usuarioConductor.getUsuarioDTO().getPersona().getId()).isPresent()) {
            model.addAttribute("error", "El documento de identidad ya está registrado.");
            model.addAttribute("usuarioRolConductorDTO", usuarioConductor);
            return "driverRegistration";
        }
        if (usuarioService.validateExistUserName(usuarioConductor.getUsuarioDTO().getUser_name())) {
            model.addAttribute("error", "El nombre de usuario ya esta registrado.");
            model.addAttribute("usuarioRolConductorDTO", usuarioConductor);
            return "driverRegistration";
        }
        try {
            UsuarioDTO usuario = usuarioConductor.getUsuarioDTO();
            RolConductorDTO rolDTO = usuarioConductor.getConductorDTO();
            if (rolDTO == null) {
                rolDTO = new RolConductorDTO();
            }
            rolDTO.setNombreRol("CONDUCTOR");

            PersonaDTO nuevaPersona = personaService.addPersonaInDB(usuario.getPersona());
            usuario.setPersona(nuevaPersona);
            RolConductor nuevoRol = new RolConductor(
                    rolDTO.getId(),
                    rolDTO.getNombreRol(),
                    rolDTO.getLicencia(),
                    rolDTO.getExperiencia());
            RolConductor rolGuardado = rolService.guardarRolConductor(nuevoRol);
            usuario.setRol(new RolConductorDTO(
                    rolGuardado.getId(),
                    rolGuardado.getNombreRol(),
                    rolGuardado.getLicencia(),
                    rolGuardado.getExperiencia()));

            usuarioService.addUsuarioInDB(usuario);
            model.addAttribute("success", "Usuario registrado exitosamente.");
            model.addAttribute("usuarioRolConductorDTO", new UsuarioRolConductorDTO());
            return "redirect:/admin/registrarConductor";
        } catch (Exception e) {
            model.addAttribute("error", "Ocurrió un error inesperado. Inténtalo de nuevo.");
            model.addAttribute("usuarioRolConductorDTO", usuarioConductor);
            return "driverRegistration";
        }

    }

    @PostMapping("/registroVehiculo")
    public String registrarVehiculo(@ModelAttribute VehiculoDTO vehiculoDTO, Model model) {
        VehiculoDTO vehiculo = vehiculoService.fetchVehiculoByPlaca(vehiculoDTO.getPlaca());
        if (vehiculo != null && vehiculo.getPlaca().equals(vehiculoDTO.getPlaca())) {
            model.addAttribute("error", "Placa ya está registrada.");
            model.addAttribute("vehiculoDTO", vehiculoDTO);
            return "driverRegistration";
        }
        try {
            vehiculoService.addVehiculoInDB(vehiculoDTO);
            model.addAttribute("success", " Vehiculo registrado exitosamente.");
            model.addAttribute("vehiculoDTO", new VehiculoDTO());
            return "redirect:/admin/registrarVehiculo";
        } catch (Exception e) {
            model.addAttribute("error", "Ocurrió un error inesperado. Inténtalo de nuevo.");
            model.addAttribute("vehiculoDTO", vehiculoDTO);
            return "vehicleRegistration";
        }
    }

    @PostMapping("/registro/Despachador")
    public String registrarDespachador(@ModelAttribute UsuarioDTO usuario, Model model) {
        if (personaService.findById(usuario.getPersona().getId()).isPresent()) {
            model.addAttribute("error", "El documento de identidad ya está registrado.");
            model.addAttribute("usuarioDTO", usuario);
            return "dispatcherRegistration";
        }
        if (usuarioService.validateExistUserName(usuario.getUser_name())) {
            model.addAttribute("error", "El nombre de usuario ya esta registrado.");
            model.addAttribute("usuarioDTO", usuario);
            return "dispatcherRegistration";
        }
        try {
            PersonaDTO nuevaPersona = personaService.addPersonaInDB(usuario.getPersona());
            RolDespachadorDTO rolDTO = (usuario.getRol() instanceof RolDespachadorDTO)
                    ? (RolDespachadorDTO) usuario.getRol()
                    : new RolDespachadorDTO();

            rolDTO.setNombreRol("DESPACHADOR");
            RolDespachador rolGuardado = rolService.guardarRolDespachador(
                    new RolDespachador(rolDTO.getId(), rolDTO.getNombreRol()));

            usuario.setPersona(nuevaPersona);
            usuario.setRol(new RolDespachadorDTO(rolGuardado.getId(), rolGuardado.getNombreRol()));

            usuarioService.addUsuarioInDB(usuario);
            model.addAttribute("success", " Usuario registrado exitosamente.");
            model.addAttribute("usuarioDTO", new UsuarioDTO());
            return "redirect:/admin/registrarDespachador";
        } catch (Exception e) {
            model.addAttribute("error", "Ocurrió un error inesperado. Inténtalo de nuevo.");
            model.addAttribute("usuarioDTO", usuario);
            return "dispatcherRegistration";
        }
    }
}
