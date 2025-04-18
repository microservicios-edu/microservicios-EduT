package com.pagosnotificaciones.pagos_notificaciones_api.service;

import com.pagosnotificaciones.pagos_notificaciones_api.model.Pago;
import com.pagosnotificaciones.pagos_notificaciones_api.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagoService {

    private final PagoRepository pagoRepository;

    @Autowired
    public PagoService(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    // Obtener todos los pagos
    public List<Pago> listarPagos() {
        return pagoRepository.findAll();
    }

    // Crear un nuevo pago
    public Pago agregarPago(Pago pago) {
        return pagoRepository.save(pago);
    }

    // Actualizar un pago existente
    public Pago actualizarPago(Long id, Pago nuevoPago) {
        Optional<Pago> existingPago = pagoRepository.findById(id);
        if (existingPago.isPresent()) {
            Pago pago = existingPago.get();
            pago.setUsuario(nuevoPago.getUsuario());
            pago.setMonto(nuevoPago.getMonto());
            return pagoRepository.save(pago);
        }
        return null;
    }

    // Eliminar un pago
    public boolean eliminarPago(Long id) {
        if (pagoRepository.existsById(id)) {
            pagoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
,