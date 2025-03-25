package com.translogistics.translogistics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.translogistics.translogistics.model.Rol;
import com.translogistics.translogistics.model.RolAdministrador;
import com.translogistics.translogistics.model.RolConductor;
import com.translogistics.translogistics.model.RolDespachador;
import com.translogistics.translogistics.repository.RolRepository;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public Rol guardarRol(Rol rol) {
        return rolRepository.save(rol);
    }

    public RolConductor guardarRolConductor(RolConductor rolConductor) {
        return rolRepository.save(rolConductor);
    }

    public RolDespachador guardarRolDespachador(RolDespachador rolDespachador) {
        return rolRepository.save(rolDespachador);
    }

    public RolAdministrador guardarRolAdministrador(RolAdministrador rolAdministrador) {
        return rolRepository.save(rolAdministrador);
    }
}