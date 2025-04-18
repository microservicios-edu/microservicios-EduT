package com.pagosnotificaciones.pagos_notificaciones_api.repository;

import com.pagosnotificaciones.pagos_notificaciones_api.model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
}
