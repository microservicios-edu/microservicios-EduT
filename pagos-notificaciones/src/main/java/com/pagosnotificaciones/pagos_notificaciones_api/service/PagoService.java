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

    public List<Pago> listarPagos() {
        return pagoRepository.findAll();
    }

    public Pago agregarPago(Pago pago) {
        return pagoRepository.save(pago);
    }

    public Pago actualizarPago(Long id, Pago nuevoPago) {
        Optional<Pago> pagoExistente = pagoRepository.findById(id);
        if (pagoExistente.isPresent()) {
            Pago pago = pagoExistente.get();
            pago.setUsuario(nuevoPago.getUsuario());
            pago.setMonto(nuevoPago.getMonto());
            return pagoRepository.save(pago);
        }
        return null;
    }

    public boolean eliminarPago(Long id) {
        return pagoRepository.deleteById(id);
    }

    // Método para verificar si un pago con el ID existe
    public boolean existePago(Long pagoId) {
        return pagoRepository.existsById(pagoId);
    }
}
