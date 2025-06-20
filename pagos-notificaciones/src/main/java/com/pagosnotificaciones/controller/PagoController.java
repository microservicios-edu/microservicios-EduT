package com.pagosnotificaciones.controller;

import com.pagosnotificaciones.model.Pago;
import com.pagosnotificaciones.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @GetMapping
    public ResponseEntity<?> obtenerTodosLosPagos() {
        List<Pago> pagos = pagoService.obtenerTodosLosPagos();
        
        if (pagos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body("No hay pagos registrados.");
        }
        
        return ResponseEntity.ok(pagos);
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPagoPorId(@PathVariable Long id) {
        Optional<Pago> pagoOptional = pagoService.obtenerPagoPorId(id);
        
        if (pagoOptional.isPresent()) {
            return ResponseEntity.ok(pagoOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body("Pago no encontrado con id: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<?> crearPago(@RequestBody Pago pago) {
        try {
            pagoService.crearPago(pago);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("✅ Pago registrado exitosamente."+"\n\tDuoc UC Alameda"+"\n\nFecha : "+pago.getFechaPago()+
                    "\nRealizado por : "+ pago.getUsuario()+
                    "\nMonto : "+pago.getMonto()+"\n________________________________"+"\nEl pago se vera reflejado en 24 Horas.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(" No se pudo registrar el pago: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPago(@PathVariable Long id, @RequestBody Pago pago) {
        try {
            pagoService.actualizarPago(id, pago);
            return ResponseEntity.ok("✅ Pago actualizado correctamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("" + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPago(@PathVariable Long id) {
        pagoService.eliminarPago(id);
        return ResponseEntity.ok("✅ Pago eliminado correctamente.");
    }
}
