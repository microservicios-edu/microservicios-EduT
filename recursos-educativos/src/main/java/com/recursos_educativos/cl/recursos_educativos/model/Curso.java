package com.recursos_educativos.cl.recursos_educativos.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cursos")
@Data               // Lombok: getters, setters, toString, equals, hashCode
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con todos los campos
@Builder            // Builder pattern opcional
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;

    private int cuposTotales;

    private int cuposDisponibles;

    private String categoria;

    private boolean activo;
}


