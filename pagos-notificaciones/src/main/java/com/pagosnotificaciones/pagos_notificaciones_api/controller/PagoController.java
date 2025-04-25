package com.pagosnotificaciones.pagos_notificaciones_api.controller;

import com.pagosnotificaciones.pagos_notificaciones_api.model.Pago;
import com.pagosnotificaciones.pagos_notificaciones_api.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public List<Pago> listarPagos() {
        return pagoService.listarPagos();
    }

    @PostMapping
    public Pago agregarPago(@RequestBody Pago pago) {
        return pagoService.agregarPago(pago);
    }

    @PutMapping("/{id}")
    public Pago actualizarPago(@PathVariable Long id, @RequestBody Pago nuevoPago) {
        return pagoService.actualizarPago(id, nuevoPago);
    }

    @DeleteMapping("/{id}")
    public String eliminarPago(@PathVariable Long id) {
        if (pagoService.eliminarPago(id)) {
            return "Pago eliminado con ID: " + id;
        }
        return "No se encontró pago con ID: " + id;
    }
}
