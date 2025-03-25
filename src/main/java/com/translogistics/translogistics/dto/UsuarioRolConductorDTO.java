package com.translogistics.translogistics.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRolConductorDTO {
    private RolConductorDTO conductorDTO;
    private UsuarioDTO usuarioDTO;
}
