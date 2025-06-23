package com.matriculainscripcion.matricula_inscripcion_api.dto;


import lombok.Data;

@Data
public class MatriculaDTO {

    private String rut;
    private String nombre;
    private String password;
    private int idCurso;
    private String nombreCurso;
    private String fechaMatricula;

}
