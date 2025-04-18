package com.pagosnotificaciones.pagos_notificaciones_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notificaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensaje;

    private String tipo;

    @ManyToOne
    @JoinColumn(name = "pago_id", nullable = false)
    private Pago pago;
}

