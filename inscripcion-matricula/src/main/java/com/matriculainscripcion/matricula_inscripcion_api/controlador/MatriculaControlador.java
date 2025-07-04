package com.matriculainscripcion.matricula_inscripcion_api.controlador;

import com.matriculainscripcion.matricula_inscripcion_api.modelo.Matricula;
import org.springframework.beans.factory.annotation.Autowired;
import com.matriculainscripcion.matricula_inscripcion_api.servicio.MatriculaServicio;
import org.springframework.web.bind.annotation.*;
import com.matriculainscripcion.matricula_inscripcion_api.dto.CursoDTO;
//import org.springframework.http.ResponseEntity;


import java.util.List;

@RestController
//Marca una clase como un controlador REST. Combina @Controller y @ResponseBody, lo que significa que los métodos devolverán directamente datos JSON o XML.
//Para exponer los endpoints HTTP (por ejemplo, /matriculas, /estudiantes, etc.), que permiten interactuar con la aplicación desde el frontend o herramientas como Postman
@RequestMapping("api/v1/matricula")
public class MatriculaControlador {
    
    @Autowired
    private MatriculaServicio matriculaServicio;

    //Método para listar todas las matrículas.
    @GetMapping
    public List<Matricula> listarMatriculas(){
        return matriculaServicio.getMatriculas();
    }
    // Método para obtener un usuario por ID
    @GetMapping("/{id}")
    public Matricula obtenerMatriculaPorId(@PathVariable int id) {
        return matriculaServicio.getMatriculaId(id);
    }

    // Método para agregar una nueva matrícula
    @PostMapping
    public Matricula agregarMatricula(@RequestBody Matricula matricula){
         return matriculaServicio.saveMatricula(matricula);
    }

    
    // Método para actualizar una matrícula existente
    @PutMapping("/{id}")
    public Matricula actualizarMatricula(@PathVariable int id, @RequestBody Matricula matricula){
        matricula.setId(id);
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
    
    @GetMapping("/cursos")
    public List<CursoDTO> listarCursosDesdeRecursosEducativos() {
        return matriculaServicio.listarCursosDisponibles();
    }
    
}