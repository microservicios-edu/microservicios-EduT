package com.pagosnotificaciones.pagos_notificaciones_api.controller;

import org.springframework.web.bind.annotation.*;

import com.pagosnotificaciones.pagos_notificaciones_api.model.Pago;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    private List<Pago> pagos = new ArrayList<>(); // Lista vacía al principio

    // GET: Devuelve todos los pagos
    /**
     * @return
     */
    @GetMapping
    public List<Pago> listarPagos() {
        return pagos; // Al inicio devolverá []
    }

    // POST: Agregar un nuevo pago
    @PostMapping
    public Pago agregarPago(@RequestBody Pago pago) {
        pagos.add(pago);  // Agrega el pago a la lista
        return pago;      // Devuelve el pago agregado
    }

    // PUT: Actualizar un pago por ID
    @PutMapping("/{id}")
    public Pago actualizarPago(@PathVariable String id, @RequestBody Pago pagoActualizado) {
        for (Pago p : pagos) {
            if (p.getId().equals(id)) {
                p.setUsuario(pagoActualizado.getUsuario());
                p.setMonto(pagoActualizado.getMonto());
                return p;  // Retorna el pago actualizado
            }
        }
        return null;  // Si no se encuentra el ID
    }

    // DELETE: Eliminar un pago por ID
    @DeleteMapping("/{id}")
    public String eliminarPago(@PathVariable String id) {
        pagos.removeIf(p -> p.getId().equals(id));  // Elimina el pago por ID
        return "Pago con ID " + id + " eliminado.";  // Mensaje de confirmación
    }
}
