package com.translogistics.translogistics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistroViajeDTO {

    private Long id;
    private String fechaViaje;
    private VehiculoDTO vehiculo;
    private UsuarioDTO conductor;
}
