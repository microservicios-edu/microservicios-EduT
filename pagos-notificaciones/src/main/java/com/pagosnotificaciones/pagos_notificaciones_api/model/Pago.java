package com.pagosnotificaciones.pagos_notificaciones_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pago {
    private Long id;
    private String usuario;
    private Double monto;
}
