package com.matriculainscripcion.matricula_inscripcion_api.dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
public class RecursoEducativoDTO { //Mapear respuestas JSON

    private int idCurso;
    private String nombreCurso;
    private String fechaMatricula;

}
