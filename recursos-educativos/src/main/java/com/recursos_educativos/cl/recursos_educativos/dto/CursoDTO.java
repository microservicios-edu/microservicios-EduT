package com.recursos_educativos.cl.recursos_educativos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

//Deuelve solo la información necesaria al microservicio de inscripción-matricula.
public class CursoDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private int cuposDisponibles;
    private boolean activo;

}
