package com.matriculainscripcion.matricula_inscripcion_api.dto;

import lombok.Data;

// el microservicio inscripción-matricula no tiene acceso directo al 
//modelo Curso del otro microservicio, debes crear un DTO local para representar el objeto
@Data
public class CursoDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private int cuposTotales;
    private int cuposDisponibles;
    private String categoria;
    private boolean activo;

}
