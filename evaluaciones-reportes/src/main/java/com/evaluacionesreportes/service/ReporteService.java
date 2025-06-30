package com.evaluacionesreportes.service;

import com.evaluacionesreportes.client.UsuarioClient;
import com.evaluacionesreportes.dto.UsuarioDTO;
import com.evaluacionesreportes.model.Reporte;
import com.evaluacionesreportes.repository.ReporteRepository;
import com.evaluacionesreportes.repository.EvaluacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
// ReporteService.java
@Service
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    @Autowired
    private UsuarioClient usuarioClient;

    public List<Reporte> obtenerTodos() {
        return reporteRepository.findAll();
    }

    public Reporte obtenerPorId(Long id) {
        return reporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado con ID: " + id));
    }

    public Reporte crear(Reporte reporte) {
        UsuarioDTO usuario = usuarioClient.obtenerUsuarioPorRut(reporte.getRutUsuario()).block();

        if (usuario == null) {
            throw new RuntimeException("El usuario con RUT " + reporte.getRutUsuario() + " no existe");
        }

        if (!esTipoValido(usuario.getTipoUsuario())) {
            throw new RuntimeException("Solo usuarios con rol profesor o administrador pueden crear reportes");
        }

        return reporteRepository.save(reporte);
    }

    public Reporte actualizar(Long id, Reporte reporteActualizado) {
        return reporteRepository.findById(id).map(r -> {
            r.setTitulo(reporteActualizado.getTitulo());
            r.setContenido(reporteActualizado.getContenido());
            r.setFecha(reporteActualizado.getFecha());
            r.setRutUsuario(reporteActualizado.getRutUsuario());
            return reporteRepository.save(r);
        }).orElseThrow(() -> new RuntimeException("Reporte no encontrado con ID: " + id));
    }

    public void eliminar(Long id) {
        reporteRepository.deleteById(id);
    }

    public boolean existePorId(Long id) {
        return reporteRepository.existsById(id);
    }

    private boolean esTipoValido(String tipoUsuario) {
        return "profesor".equalsIgnoreCase(tipoUsuario) || "administrador".equalsIgnoreCase(tipoUsuario);
    }
}
