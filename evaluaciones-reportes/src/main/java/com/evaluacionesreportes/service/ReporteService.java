package com.evaluacionesreportes.service;

import com.evaluacionesreportes.model.Reporte;
import com.evaluacionesreportes.repository.ReporteRepository;
import com.evaluacionesreportes.repository.EvaluacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    public List<Reporte> obtenerTodos() {
        return reporteRepository.findAll();
    }

    public Reporte obtenerPorId(Long id) {
        return reporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado con ID: " + id));
    }

    public Reporte crear(Reporte reporte) {
        if (evaluacionRepository.count() == 0) {
            throw new RuntimeException("No se puede crear el reporte porque no hay evaluaciones registradas.");
        }
        return reporteRepository.save(reporte);
    }

    public Reporte actualizar(Long id, Reporte reporteActualizado) {
        return reporteRepository.findById(id).map(r -> {
            r.setTitulo(reporteActualizado.getTitulo());
            r.setContenido(reporteActualizado.getContenido());
            r.setFecha(reporteActualizado.getFecha());
            return reporteRepository.save(r);
        }).orElseThrow(() -> new RuntimeException("Reporte no encontrado con ID: " + id));
    }

    public void eliminar(Long id) {
        reporteRepository.deleteById(id);
    }

    public boolean existePorId(Long id) {
        return reporteRepository.existsById(id);
    }
}