package com.pagosnotificaciones.service;

import com.pagosnotificaciones.model.Pago;
import com.pagosnotificaciones.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    public List<Pago> obtenerTodosLosPagos() {
        return pagoRepository.findAll();
    }

    public Optional<Pago> obtenerPagoPorId(Long id) {
        return pagoRepository.findById(id);
    }

    public Pago crearPago(Pago pago) {
        boolean existe = pagoRepository.existsByUsuarioAndMontoAndFechaPago(
                pago.getUsuario(), pago.getMonto(), pago.getFechaPago());

        if (existe) {
            throw new RuntimeException("Ya existe un pago con esos datos.");
        }

        return pagoRepository.save(pago);
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
