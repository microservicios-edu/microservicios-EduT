package com.matriculainscripcion.matricula_inscripcion_api.servicio;

import com.matriculainscripcion.matricula_inscripcion_api.modelo.Matricula;
import com.matriculainscripcion.matricula_inscripcion_api.repositorio.MatriculaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatriculaServicio {
    
    @Autowired
    private MatriculaRepositorio matriculaRepositorio;

    public List<Matricula> getMatriculas(){
        return matriculaRepositorio.findAll();
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
