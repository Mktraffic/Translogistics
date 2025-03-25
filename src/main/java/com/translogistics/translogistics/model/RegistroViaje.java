package com.translogistics.translogistics.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "registro_viaje")
@Data
@AllArgsConstructor
public class RegistroViaje {

    @Id
    @Column(name = "id_registro", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_viaje", length = 50)
    @Size(min = 3, max = 50)
    private String fechaViaje;

    @OneToOne
    @JoinColumn(name = "placa_vehiculo", unique = true, nullable = false)
    private Vehiculo vehiculo;

    @OneToOne
    @JoinColumn(name = "id_conductor", unique = true, nullable = false)
    private Usuario conductor; 

}
