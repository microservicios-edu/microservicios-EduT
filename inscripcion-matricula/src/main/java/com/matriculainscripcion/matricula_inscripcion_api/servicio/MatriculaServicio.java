package com.matriculainscripcion.matricula_inscripcion_api.servicio;

import com.matriculainscripcion.matricula_inscripcion_api.modelo.Matricula;
import com.matriculainscripcion.matricula_inscripcion_api.repositorio.MatriculaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.matriculainscripcion.matricula_inscripcion_api.cliente.CursoCliente;
import com.matriculainscripcion.matricula_inscripcion_api.dto.CursoDTO;

import java.util.List;
import java.util.Optional;

@Service
//Declara una clase como componente del servicio. Contiene la lógica de negocio (como validar datos, hacer cálculos o decidir qué repositorio usar).
//Para separar la lógica de negocio del controlador y mantener una estructura limpia.
public class MatriculaServicio {
    
    @Autowired
    private MatriculaRepositorio matriculaRepositorio;

    @Autowired
    private CursoCliente cursoCliente;

    public List<Matricula> getMatriculas(){
        return matriculaRepositorio.findAll();
    }

    public List<CursoDTO> listarCursosDisponibles() { // consultar los cursos disponibles
        return cursoCliente.obtenerCursos();
    }

    public Matricula saveMatricula(Matricula matricula){
        return matriculaRepositorio.save(matricula);
    }

    public Matricula getMatriculaId(int id){
        Optional<Matricula> optional = matriculaRepositorio.findById(id);
        return optional.orElse(null);
    }

    public Matricula updateMatricula(Matricula matricula){
        return matriculaRepositorio.save(matricula);
    }

    public boolean deleteMatricula(int id){
        if(matriculaRepositorio.existsById(id)){
            matriculaRepositorio.deleteById(id);
            return true;
        }
        return false;
    }
    
}
