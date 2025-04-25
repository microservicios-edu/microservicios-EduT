package com.pagosnotificaciones.pagos_notificaciones_api.repository;

import com.pagosnotificaciones.pagos_notificaciones_api.model.Notificacion;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class NotificacionRepository {

    private final List<Notificacion> notificaciones = new ArrayList<>();
    private Long currentId = 1L;

    public List<Notificacion> findAll() {
        return notificaciones;
    }

    public Optional<Notificacion> findById(Long id) {
        return notificaciones.stream().filter(n -> n.getId().equals(id)).findFirst();
    }

    public Notificacion save(Notificacion notificacion) {
        if (notificacion.getId() == null) {
            notificacion.setId(currentId++);
            notificaciones.add(notificacion);
        } else {
            notificaciones.replaceAll(n -> n.getId().equals(notificacion.getId()) ? notificacion : n);
        }
        return notificacion;
    }

    public boolean deleteById(Long id) {
        return notificaciones.removeIf(n -> n.getId().equals(id));
    }
}
