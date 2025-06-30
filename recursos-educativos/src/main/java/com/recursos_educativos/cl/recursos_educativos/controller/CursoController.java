package com.recursos_educativos.cl.recursos_educativos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recursos_educativos.cl.recursos_educativos.dto.CursoDTO;
import com.recursos_educativos.cl.recursos_educativos.model.Curso;
import com.recursos_educativos.cl.recursos_educativos.service.CursoService;

@RestController
@RequestMapping("/api/v1/cursos")
@CrossOrigin(origins = "*") // Habilita peticiones desde el frontend
public class CursoController {

    @Autowired
    private CursoService cursoService;

    // Endpoint para todos los cursos (administración interna)
    @GetMapping
    public List<Curso> listarCursos() {
        return cursoService.listarCursos();
    }

    // Endpoint público para otros microservicios (como inscripción-matricula)
    @GetMapping("/disponibles")
    public List<CursoDTO> listarCursosDisponibles() {
        return cursoService.listarCursosDisponibles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> obtenerCurso(@PathVariable Long id) {
        return cursoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Curso crearCurso(@RequestBody Curso curso) {
        return cursoService.crearCurso(curso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> actualizarCurso(@PathVariable Long id, @RequestBody Curso curso) {
        try {
            return ResponseEntity.ok(cursoService.actualizarCurso(id, curso));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable Long id) {
        cursoService.eliminarCurso(id);
        return ResponseEntity.noContent().build();
    }
}

