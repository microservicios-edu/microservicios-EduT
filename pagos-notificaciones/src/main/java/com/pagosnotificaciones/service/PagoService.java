package com.pagosnotificaciones.service;

import com.pagosnotificaciones.model.Notificacion;
import com.pagosnotificaciones.model.Pago;
import com.pagosnotificaciones.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;
    @Autowired
    private NotificacionService notificacionService; // inyectamos el servicio

    public List<Pago> obtenerTodosLosPagos() {
        return pagoRepository.findAll();
    }

    public Optional<Pago> obtenerPagoPorId(Long id) {
        return pagoRepository.findById(id);
    }

    public Pago crearPago(Pago pago) {
        // Guardar el pago
        Pago pagoGuardado = pagoRepository.save(pago);

        // Crear notificación automáticamente
        Notificacion notificacion = new Notificacion();
        notificacion.setMensaje("✅ Se ha registrado un pago de " + pago.getMonto() +
                " por parte del usuario " + pago.getUsuario());
        notificacion.setFechaEnvio(LocalDate.now());
        notificacion.setPagoId(pagoGuardado.getId());

        // Guardar la notificación
        notificacionService.guardarNotificacion(notificacion);

        return pagoGuardado;
    }

    public Pago actualizarPago(Long id, Pago pagoActualizado) {
        return pagoRepository.findById(id)
                .map(pago -> {
                    pago.setUsuario(pagoActualizado.getUsuario());
                    pago.setMonto(pagoActualizado.getMonto());
                    pago.setFechaPago(pagoActualizado.getFechaPago());
                    return pagoRepository.save(pago);
                }).orElseThrow(() -> new RuntimeException("Pago no encontrado con id " + id));
    }

    public void eliminarPago(Long id) {
        pagoRepository.deleteById(id);
    }
}
