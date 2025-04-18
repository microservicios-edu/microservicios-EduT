package com.pagosnotificaciones.pagos_notificaciones_api.controller;

import com.pagosnotificaciones.pagos_notificaciones_api.model.Pago;
import com.pagosnotificaciones.pagos_notificaciones_api.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    private final PagoService pagoService;

    @Autowired
    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    // Obtener todos los pagos
    @GetMapping
    public List<Pago> listarPagos() {
        return pagoService.listarPagos();
    }

    // Crear un nuevo pago
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pago agregarPago(@RequestBody Pago pago) {
        return pagoService.agregarPago(pago);
    }

    // Actualizar un pago existente
    @PutMapping("/{id}")
    public Pago actualizarPago(@PathVariable Long id, @RequestBody Pago nuevoPago) {
        return pagoService.actualizarPago(id, nuevoPago);
    }

    // Eliminar un pago
    @DeleteMapping("/{id}")
    public String eliminarPago(@PathVariable Long id) {
        if (pagoService.eliminarPago(id)) {
            return "Pago con ID " + id + " eliminado.";
        } else {
            return "No se encontró el pago con ID " + id;
        }
    }
}
