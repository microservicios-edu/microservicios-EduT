package com.pagosnotificaciones.repository;

import com.pagosnotificaciones.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface PagoRepository extends JpaRepository<Pago, Long> {
    boolean existsByUsuarioAndMontoAndFechaPago(String usuario, Double monto, LocalDate fechaPago);
}
