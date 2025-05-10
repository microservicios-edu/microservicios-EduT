package com.pagosnotificaciones.repository;

import com.pagosnotificaciones.model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
    List<Notificacion> findByPagoId(Long pagoId);
}
