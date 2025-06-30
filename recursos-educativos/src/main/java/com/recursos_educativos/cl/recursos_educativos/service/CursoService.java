package com.recursos_educativos.cl.recursos_educativos.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recursos_educativos.cl.recursos_educativos.dto.CursoDTO;
import com.recursos_educativos.cl.recursos_educativos.model.Curso;
import com.recursos_educativos.cl.recursos_educativos.repository.CursoRepository;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    public List<CursoDTO> listarCursosDisponibles() { //Se actualiza service para lista de cursos activos
            return cursoRepository.findAll().stream()
                    .filter(Curso::isActivo)
                    .map(curso -> new CursoDTO(
                            curso.getId(),
                            curso.getNombre(),
                            curso.getDescripcion(),
                            curso.getCuposDisponibles(),
                            curso.isActivo()
                    ))
                    .collect(Collectors.toList());
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

