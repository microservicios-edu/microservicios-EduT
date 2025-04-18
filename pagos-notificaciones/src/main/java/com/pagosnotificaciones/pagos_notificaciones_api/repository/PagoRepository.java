package com.pagosnotificaciones.pagos_notificaciones_api.repository;

import com.pagosnotificaciones.pagos_notificaciones_api.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoRepository extends JpaRepository<Pago, Long> {
}
