package com.pagosnotificaciones.pagos_notificaciones_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notificacion {
    private Long id;
    private String mensaje;
    private String tipo;
    private Long pagoId; // Referencia al ID de Pago
}
