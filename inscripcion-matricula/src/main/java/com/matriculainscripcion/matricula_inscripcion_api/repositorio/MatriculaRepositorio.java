package com.matriculainscripcion.matricula_inscripcion_api.repositorio;

import com.matriculainscripcion.matricula_inscripcion_api.modelo.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//Indica que la clase es un repositorio de acceso a datos. Spring lo utiliza para implementar automáticamente los métodos de base de datos.
public interface  MatriculaRepositorio extends JpaRepository<Matricula, Integer>{

}