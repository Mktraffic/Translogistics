package com.translogistics.translogistics.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "persona")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persona {

    @Id
    @Column(name = "id_persona", length = 50, nullable = false)
    private Long id;

    @Column(name = "nombre", length = 100)
    @Size(min = 3, max = 100)
    private String nombre;

    @Column(name = "apellido", length = 100)
    @Size(min = 3, max = 100)
    private String apellido;
     

}