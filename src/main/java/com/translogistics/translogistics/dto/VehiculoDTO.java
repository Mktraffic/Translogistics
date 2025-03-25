package com.translogistics.translogistics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehiculoDTO {
    private String placa;
    private String modelo;
    private int anio;
    private String tipo;
    private String estado;
}
