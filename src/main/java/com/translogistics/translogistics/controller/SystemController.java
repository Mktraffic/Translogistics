package com.translogistics.translogistics.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.translogistics.translogistics.dto.PersonaDTO;
import com.translogistics.translogistics.dto.RolAdministradorDTO;
import com.translogistics.translogistics.dto.UsuarioDTO;
import com.translogistics.translogistics.dto.UsuarioRolConductorDTO;
import com.translogistics.translogistics.dto.VehiculoDTO;
import com.translogistics.translogistics.model.RolAdministrador;
import com.translogistics.translogistics.service.PersonaService;
import com.translogistics.translogistics.service.RolService;
import com.translogistics.translogistics.service.UsuarioService;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class SystemController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PersonaService personaService;

    @Autowired
    private RolService rolService;

    @PostMapping("/registro/administrador")
    public String registerAdmin(@ModelAttribute UsuarioDTO administrador, Model model) {
        if (personaService.findById(administrador.getPersona().getId()).isPresent()) {
            model.addAttribute("error", "El documento de identidad ya está registrado.");
            model.addAttribute("usuarioDTO", administrador);
            return "adminRegistration";
        }
        if (usuarioService.validateExistUserName(administrador.getUser_name())) {
            model.addAttribute("error", "El nombre de usuario ya esta registrado.");
            model.addAttribute("usuarioDTO", administrador);
            return "adminRegistration";
        }
        try {
            PersonaDTO persona = new PersonaDTO(
                    administrador.getPersona().getId(),
                    administrador.getPersona().getNombre(),
                    administrador.getPersona().getApellido());

            PersonaDTO nuevaPersona = personaService.addPersonaInDB(persona);
            RolAdministradorDTO rolAdministradorDTO;
            if (administrador.getRol() instanceof RolAdministradorDTO) {
                rolAdministradorDTO = (RolAdministradorDTO) administrador.getRol();
            } else {
                rolAdministradorDTO = new RolAdministradorDTO();
                rolAdministradorDTO.setNombreRol("ADMINISTRADOR");
            }

            RolAdministrador nuevoRol = new RolAdministrador(
                    rolAdministradorDTO.getId(),
                    rolAdministradorDTO.getNombreRol());

            RolAdministrador rolGuardado = rolService.guardarRolAdministrador(nuevoRol);

            administrador.setPersona(nuevaPersona);
            administrador.setRol(new RolAdministradorDTO(rolGuardado.getId(), rolGuardado.getNombreRol()));

            usuarioService.addUsuarioInDB(administrador);
            model.addAttribute("success", "Administrador registrado correctamente.");
            model.addAttribute("usuarioDTO", new UsuarioDTO());
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("error", "Ocurrió un error inesperado. Inténtalo de nuevo.");
            model.addAttribute("usuarioDTO", administrador);
            return "adminRegistration";
        }
    }

    @PostMapping("/loggeo")
    public String procesarLogin(@ModelAttribute UsuarioDTO usuarioDTO, Model model, HttpSession session) {
        try {
            String[] data = usuarioService
                    .validateUserByUserName(usuarioDTO.getUser_name(), usuarioDTO.getUser_password())
                    .split(",");
            boolean isAuthenticated = Boolean.parseBoolean(data[0].trim());
            if (!isAuthenticated) {
                String errorCode = data.length > 1 ? data[1].trim() : "UNKNOWN_ERROR";
                String mensajeError = "";
                switch (errorCode) {
                    case "USER_NOT_FOUND":
                        mensajeError = "El usuario no existe.";
                        break;
                    case "WRONG_PASSWORD":
                        mensajeError = "Contraseña incorrecta.";
                        break;
                    default:
                        mensajeError = "Credenciales incorrectas.";
                        break;
                }

                model.addAttribute("error", mensajeError);
                return "userLogging";
            }
            String rol = data[1];
            session.setAttribute("usuario", searchPersonByUserName(usuarioDTO.getUser_name()));
            session.setAttribute("rol", rol);
            switch (rol) {
                case "ADMINISTRADOR":
                    return "administratorOptions";
                case "DESPACHADOR":
                    return "dispatcherOptions";
                case "CONDUCTOR":
                    model.addAttribute("error", "Sin acceso al sistema");
                    return "userLogging";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Ocurrió un error inesperado. Inténtalo de nuevo.");
            return "userLogging";
        }
        return null;
    }

    public String searchPersonByUserName(String user_name) {
        List<UsuarioDTO> userList = usuarioService.findAllUsuarios();
        String nombre = "";
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUser_name().equals(user_name)) {
                nombre = userList.get(i).getPersona().getNombre() + " " + userList.get(i).getPersona().getApellido();
            }
        }
        return nombre;
    }
    // Navegación entre vistas

    @GetMapping("/")
    public String mostrarFormularioAdmin(Model model,
                                         @RequestParam(value = "success", required = false) String success,
                                         @RequestParam(value = "error", required = false) String error) {
        model.addAttribute("usuarioDTO", new UsuarioDTO());
        if (success != null) {
            model.addAttribute("mensajeExito", success);
        }
        if (error != null) {
            model.addAttribute("mensajeError", error);
        }
        return "adminRegistration";
    }

    @GetMapping("/despachador/opciones")
    public String opcionesDespachador(Model model) {
        model.addAttribute("usuarioDTO", new UsuarioDTO());
        return "dispatcherOptions";
    }

    @PostMapping("/logout")
    public String cerrarSesion() {
        return "redirect:/login";
    }

    @GetMapping("/dashboard/dispatcher")
    public String volverADispatcherOptions(Model model) {
        model.addAttribute("usuarioDTO", new UsuarioDTO());
        return "dispatcherOptions";
    }

    @GetMapping("/dashboard")
    public String volverAdministratorOptions(Model model) {
        model.addAttribute("usuarioDTO", new UsuarioDTO());
        return "administratorOptions";
    }

    @GetMapping("/dashboard/admRegistration")
    public String volverAdministratorRegistration(Model model) {
        model.addAttribute("usuarioDTO", new UsuarioDTO());
        return "adminRegistration";
    }

    @GetMapping("/login")
    public String mostrarLogin(Model model,
                               @RequestParam(value = "success", required = false) String success,
                               @RequestParam(value = "error", required = false) String error) {
        model.addAttribute("usuarioDTO", new UsuarioDTO());
        if (success != null) {
            model.addAttribute("mensajeExito", success);
        }
        if (error != null) {
            model.addAttribute("mensajeError", error);
        }
        return "userLogging";
    }

    @GetMapping("/admin/registrarDespachador")
    public String mostrarFormularioDespachador(Model model,
                                               @RequestParam(value = "success", required = false) String success,
                                               @RequestParam(value = "error", required = false) String error) {
        model.addAttribute("usuarioDTO", new UsuarioDTO());

        if (success != null) {
            model.addAttribute("mensajeExito", success);
        }
        if (error != null) {
            model.addAttribute("mensajeError", error);
        }
        return "dispatcherRegistration";
    }

    @GetMapping("/admin/registrarVehiculo")
    public String mostrarRegistroVehiculo(Model model,
                                          @RequestParam(value = "success", required = false) String success,
                                          @RequestParam(value = "error", required = false) String error) {
        model.addAttribute("vehiculoDTO", new VehiculoDTO());
        if (success != null) {
            model.addAttribute("mensajeExito", success);
        }
        if (error != null) {
            model.addAttribute("mensajeError", error);
        }
        return "vehicleRegistration";
    }

    @GetMapping("/admin/registrarConductor")
    public String mostrarRegistroConductor(Model model,
                                           @RequestParam(value = "success", required = false) String success,
                                           @RequestParam(value = "error", required = false) String error) {
        model.addAttribute("usuarioRolConductorDTO", new UsuarioRolConductorDTO());
        if (success != null) {
            model.addAttribute("mensajeExito", success);
        }
        if (error != null) {
            model.addAttribute("mensajeError", error);
        }
        return "driverRegistration";
    }

}