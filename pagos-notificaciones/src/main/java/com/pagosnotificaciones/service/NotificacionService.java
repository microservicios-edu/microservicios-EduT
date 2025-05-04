package com.pagosnotificaciones.service;

import com.pagosnotificaciones.model.Notificacion;
import com.pagosnotificaciones.repository.NotificacionRepository;
import com.pagosnotificaciones.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    @Autowired
    private PagoRepository pagoRepository;

    public List<Notificacion> obtenerTodasLasNotificaciones() {
        return notificacionRepository.findAll();
    }

    public Optional<Notificacion> obtenerNotificacionPorId(Long id) {
        return notificacionRepository.findById(id);
    }

    public List<Notificacion> obtenerNotificacionesPorPagoId(Long pagoId) {
        return notificacionRepository.findByPagoId(pagoId);
    }

    public Notificacion guardarNotificacion(Notificacion notificacion) {
        if (notificacion.getPagoId() == null || !pagoRepository.existsById(notificacion.getPagoId())) {
            throw new RuntimeException("No se puede crear la notificación porque el pago no existe.");
        }
        notificacion.setFechaEnvio(LocalDate.now());
        return notificacionRepository.save(notificacion);
    }

    public Notificacion actualizarNotificacion(Long id, Notificacion actualizada) {
        return notificacionRepository.findById(id).map(n -> {
            n.setMensaje(actualizada.getMensaje());
            n.setPagoId(actualizada.getPagoId());
            n.setFechaEnvio(LocalDate.now());
            return notificacionRepository.save(n);
        }).orElseThrow(() -> new RuntimeException("No se encontró la notificación con ID: " + id));
    }

    public void eliminarNotificacion(Long id) {
        if (!notificacionRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. La notificación no existe con ID: " + id);
        }
        notificacionRepository.deleteById(id);
    }
}
