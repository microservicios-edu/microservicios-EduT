package com.matriculainscripcion.matricula_inscripcion_api.repositorio;

import com.matriculainscripcion.matricula_inscripcion_api.modelo.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  MatriculaRepositorio extends JpaRepository<Matricula, Integer>{

}
