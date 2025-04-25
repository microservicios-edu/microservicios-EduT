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

    public List<Notificacion> listarNotificaciones() {
        return notificacionRepository.findAll();
    }

    public Notificacion agregarNotificacion(Notificacion notificacion) {
        return notificacionRepository.save(notificacion);
    }

    public Notificacion actualizarNotificacion(Long id, Notificacion nuevaNotificacion) {
        Optional<Notificacion> existente = notificacionRepository.findById(id);
        if (existente.isPresent()) {
            Notificacion n = existente.get();
            n.setMensaje(nuevaNotificacion.getMensaje());
            n.setTipo(nuevaNotificacion.getTipo());
            n.setPagoId(nuevaNotificacion.getPagoId());
            return notificacionRepository.save(n);
        }
        return null;
    }

    public boolean eliminarNotificacion(Long id) {
        return notificacionRepository.deleteById(id);
    }
}
