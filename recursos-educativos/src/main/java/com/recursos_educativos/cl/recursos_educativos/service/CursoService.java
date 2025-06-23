package com.recursos_educativos.cl.recursos_educativos.service;

import com.recursos_educativos.cl.recursos_educativos.model.Curso;
import com.recursos_educativos.cl.recursos_educativos.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    public Optional<Curso> buscarPorId(Long id) {
        return cursoRepository.findById(id);
    }

    public Curso crearCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Curso actualizarCurso(Long id, Curso cursoActualizado) {
        return cursoRepository.findById(id)
                .map(curso -> {
                    curso.setNombre(cursoActualizado.getNombre());
                    curso.setDescripcion(cursoActualizado.getDescripcion());
                    curso.setCuposTotales(cursoActualizado.getCuposTotales());
                    curso.setCuposDisponibles(cursoActualizado.getCuposDisponibles());
                    curso.setCategoria(cursoActualizado.getCategoria());
                    curso.setActivo(cursoActualizado.isActivo());
                    return cursoRepository.save(curso);
                })
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
    }

    public void eliminarCurso(Long id) {
        cursoRepository.deleteById(id);
    }
}

