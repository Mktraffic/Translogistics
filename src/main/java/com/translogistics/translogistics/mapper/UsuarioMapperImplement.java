package com.translogistics.translogistics.mapper;

import com.translogistics.translogistics.dto.RolAdministradorDTO;
import com.translogistics.translogistics.dto.RolConductorDTO;
import com.translogistics.translogistics.dto.RolDespachadorDTO;
import com.translogistics.translogistics.dto.UsuarioDTO;
import com.translogistics.translogistics.model.RolAdministrador;
import com.translogistics.translogistics.model.RolConductor;
import com.translogistics.translogistics.model.RolDespachador;
import com.translogistics.translogistics.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapperImplement {
    @Autowired
    private PersonaMapper personaMapper;

    @Override
    public UsuarioDTO toDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setUser_name(usuario.getUser_name());
        usuarioDTO.setUser_password(usuario.getUser_password());
        usuarioDTO.setPersona(personaMapper.toDTO(usuario.getPersona()));

        if (usuario.getRol() instanceof RolAdministrador) {
            RolAdministrador rolAdmin = (RolAdministrador) usuario.getRol();
            usuarioDTO.setRol(new RolAdministradorDTO(rolAdmin.getId(), rolAdmin.getNombreRol()));
        } else if (usuario.getRol() instanceof RolConductor) {
            RolConductor rolConductor = (RolConductor) usuario.getRol();
            usuarioDTO.setRol(new RolConductorDTO(rolConductor.getId(), "conductor", rolConductor.getLicencia(), rolConductor.getExperiencia()));
        } else if (usuario.getRol() instanceof RolDespachador) {
            RolDespachador rolDespachador = (RolDespachador) usuario.getRol();
            usuarioDTO.setRol(new RolDespachadorDTO(rolDespachador.getId(), rolDespachador.getNombreRol()));
        }

        return usuarioDTO;
    }

    @Override
    public Usuario toEntity(UsuarioDTO usuarioDTO) {
        if (usuarioDTO == null) {
            return null;
        }

        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.getId());
        usuario.setUser_name(usuarioDTO.getUser_name());
        usuario.setUser_password(usuarioDTO.getUser_password());
        usuario.setPersona(personaMapper.toEntity(usuarioDTO.getPersona()));
        if (usuarioDTO.getRol() != null) {
            if (usuarioDTO.getRol() instanceof RolAdministradorDTO) {
                RolAdministradorDTO rolAdminDTO = (RolAdministradorDTO) usuarioDTO.getRol();
                usuario.setRol(new RolAdministrador(rolAdminDTO.getId(), rolAdminDTO.getNombreRol()));
            } else if (usuarioDTO.getRol() instanceof RolConductorDTO) {
                RolConductorDTO rolConductorDTO = (RolConductorDTO) usuarioDTO.getRol();
                usuario.setRol(new RolConductor(rolConductorDTO.getId(), rolConductorDTO.getNombreRol(), rolConductorDTO.getLicencia(), rolConductorDTO.getExperiencia()));
            } else if (usuarioDTO.getRol() instanceof RolDespachadorDTO) {
                RolDespachadorDTO rolDespachadorDTO = (RolDespachadorDTO) usuarioDTO.getRol();
                usuario.setRol(new RolDespachador(rolDespachadorDTO.getId(), rolDespachadorDTO.getNombreRol()));
            }
        } else {
            throw new RuntimeException("Error: El usuario debe tener un rol asignado.");
        }
        return usuario;
    }
}
