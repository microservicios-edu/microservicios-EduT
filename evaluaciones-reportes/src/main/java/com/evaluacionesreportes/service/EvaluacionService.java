package com.evaluacionesreportes.service;

import com.evaluacionesreportes.model.Evaluacion;
import com.evaluacionesreportes.repository.EvaluacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluacionService {

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    public List<Evaluacion> obtenerTodas() {
        return evaluacionRepository.findAll();
    }

    public Evaluacion obtenerPorId(Long id) {
        return evaluacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluación no encontrada con ID: " + id));
    }

    public Evaluacion crear(Evaluacion evaluacion) {
        return evaluacionRepository.save(evaluacion);
    }

    public Evaluacion actualizar(Long id, Evaluacion evaluacionActualizada) {
        return evaluacionRepository.findById(id).map(e -> {
            e.setNombre(evaluacionActualizada.getNombre());
            e.setDescripcion(evaluacionActualizada.getDescripcion());
            e.setPuntaje(evaluacionActualizada.getPuntaje());
            e.setFecha(evaluacionActualizada.getFecha());
            return evaluacionRepository.save(e);
        }).orElseThrow(() -> new RuntimeException("No se puede actualizar. Evaluación no encontrada con ID: " + id));
    }

    public void eliminar(Long id) {
        evaluacionRepository.deleteById(id);
    }

    public boolean existePorId(Long id) {
        return evaluacionRepository.existsById(id);
    }
}