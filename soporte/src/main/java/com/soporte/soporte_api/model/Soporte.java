package com.soporte.soporte_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "soporte")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Soporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombreUsuario;
    private String correo;
    private String comentario;
    private String estado;
    private String fechaCreacion;
}