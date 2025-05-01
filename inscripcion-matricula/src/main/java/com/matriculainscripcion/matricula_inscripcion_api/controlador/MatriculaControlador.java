package com.matriculainscripcion.matricula_inscripcion_api.controlador;

import com.matriculainscripcion.matricula_inscripcion_api.modelo.Matricula;
import org.springframework.beans.factory.annotation.Autowired;
import com.matriculainscripcion.matricula_inscripcion_api.servicio.MatriculaServicio;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


import java.util.List;

@RestController
@RequestMapping("/matricula")
public class MatriculaControlador {
    
    @Autowired
    private MatriculaServicio matriculaServicio;

    //Método para listar todas las matrículas
    @GetMapping
    public List<Matricula> listarMatriculas(){
        return matriculaServicio.getMatriculas();
    }

    // Método para agregar una nueva matrícula
    @PostMapping
    public Matricula agregarMatricula(@RequestBody Matricula matricula){
        return matriculaServicio.saveMatricula(matricula);
    }

    // Método para actualizar una matrícula existente
    @PutMapping("/{id}")
    public Matricula actualizarMatricula(@PathVariable int id, @RequestBody Matricula matricula){
        return matriculaServicio.updateMatricula(matricula);
    }

    //Método para eliminar una matrícula
    @DeleteMapping("/{id}")
    public String eliminarMatricula(@PathVariable int id){

        if(matriculaServicio.deleteMatricula(id)){
            return "Matrícula eliminada";
        } else {
            return "Matrícula no encontrada";
        }
    }
}