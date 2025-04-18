package com.pagosnotificaciones.pagos_notificaciones_api.service;

import com.pagosnotificaciones.pagos_notificaciones_api.model.Notificacion;
import com.pagosnotificaciones.pagos_notificaciones_api.repository.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificacionService {

    private final NotificacionRepository notificacionRepository;

    @Autowired
    public NotificacionService(NotificacionRepository notificacionRepository) {
        this.notificacionRepository = notificacionRepository;
    }

    // Obtener todas las notificaciones
    public List<Notificacion> listarNotificaciones() {
        return notificacionRepository.findAll();
    }

    // Crear una nueva notificación
    public Notificacion agregarNotificacion(Notificacion notificacion) {
        return notificacionRepository.save(notificacion);
    }

    // Actualizar una notificación existente
    public Notificacion actualizarNotificacion(Long id, Notificacion nuevaNotificacion) {
        Optional<Notificacion> existingNotificacion = notificacionRepository.findById(id);
        if (existingNotificacion.isPresent()) {
            Notificacion notificacion = existingNotificacion.get();
            notificacion.setMensaje(nuevaNotificacion.getMensaje());
            notificacion.setTipo(nuevaNotificacion.getTipo());
            notificacion.setPago(nuevaNotificacion.getPago());  // Si es necesario actualizar el pago asociado
            return notificacionRepository.save(notificacion);
        }
        return null;
    }

    // Eliminar una notificación
    public boolean eliminarNotificacion(Long id) {
        if (notificacionRepository.existsById(id)) {
            notificacionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
