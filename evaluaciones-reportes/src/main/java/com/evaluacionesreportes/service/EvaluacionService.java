package com.evaluacionesreportes.service;

import com.evaluacionesreportes.client.UsuarioClient;
import com.evaluacionesreportes.dto.UsuarioDTO;
import com.evaluacionesreportes.model.Evaluacion;
import com.evaluacionesreportes.model.Reporte;
import com.evaluacionesreportes.repository.EvaluacionRepository;
import com.evaluacionesreportes.repository.ReporteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDate;

@Service
public class EvaluacionService {

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    @Autowired
    private ReporteRepository reporteRepository;

    @Autowired
    private UsuarioClient usuarioClient;

    public List<Evaluacion> obtenerTodas() {
        return evaluacionRepository.findAll();
    }

    public Evaluacion obtenerPorId(Long id) {
        return evaluacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluación no encontrada con ID: " + id));
    }

    public Evaluacion crear(Evaluacion evaluacion) {
        // Obtener usuario por rut
        UsuarioDTO usuario = usuarioClient.obtenerUsuarioPorRut(evaluacion.getRutUsuario()).block();

        if (usuario == null) {
            throw new RuntimeException("El usuario con RUT " + evaluacion.getRutUsuario() + " no existe");
        }

        if (!esTipoValido(usuario.getTipoUsuario())) {
            throw new RuntimeException("Solo usuarios con rol profesor o administrador pueden crear evaluaciones");
        }

        // Guardar evaluación
        Evaluacion nueva = evaluacionRepository.save(evaluacion);

        // Crear reporte automático
        Reporte autoReporte = new Reporte();
        autoReporte.setTitulo("Evaluación creada");
        autoReporte.setContenido("Se registró la evaluación '" + nueva.getNombre() +
                "' con puntaje " + nueva.getPuntaje() +
                " por el usuario '" + usuario.getNombre() +
                "' (RUT: " + usuario.getRut() + ") el " + nueva.getFecha() + ".");
        autoReporte.setFecha(LocalDate.now());

        // Guardar el reporte
        reporteRepository.save(autoReporte);

        return nueva;
    }

    public Evaluacion actualizar(Long id, Evaluacion evaluacionActualizada) {
        return evaluacionRepository.findById(id).map(e -> {
            e.setNombre(evaluacionActualizada.getNombre());
            e.setDescripcion(evaluacionActualizada.getDescripcion());
            e.setPuntaje(evaluacionActualizada.getPuntaje());
            e.setFecha(evaluacionActualizada.getFecha());
            e.setRutUsuario(evaluacionActualizada.getRutUsuario());
            return evaluacionRepository.save(e);
        }).orElseThrow(() -> new RuntimeException("No se puede actualizar. Evaluación no encontrada con ID: " + id));
    }

    public void eliminar(Long id) {
        evaluacionRepository.deleteById(id);
    }

    public boolean existePorId(Long id) {
        return evaluacionRepository.existsById(id);
    }

    private boolean esTipoValido(String tipoUsuario) {
        return "profesor".equalsIgnoreCase(tipoUsuario) || "administrador".equalsIgnoreCase(tipoUsuario);
    }
}
