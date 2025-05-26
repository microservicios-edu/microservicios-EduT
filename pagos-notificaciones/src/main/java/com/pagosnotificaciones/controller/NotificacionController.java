package com.pagosnotificaciones.controller;

import com.pagosnotificaciones.model.Notificacion;
import com.pagosnotificaciones.service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @GetMapping
    public ResponseEntity<?> obtenerTodasLasNotificaciones() {
        List<Notificacion> notificaciones = notificacionService.obtenerTodasLasNotificaciones();
        if (notificaciones.isEmpty()) {
            return new ResponseEntity<>("No hay notificaciones registradas.", HttpStatus.OK);
        }
        return new ResponseEntity<>(notificaciones, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notificacion> obtenerNotificacionPorId(@PathVariable Long id) {
        Optional<Notificacion> notificacion = notificacionService.obtenerNotificacionPorId(id);
        return notificacion
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    

    @GetMapping("/pago/{pagoId}")
    public ResponseEntity<?> obtenerNotificacionesPorPagoId(@PathVariable Long pagoId) {
        List<Notificacion> notificaciones = notificacionService.obtenerNotificacionesPorPagoId(pagoId);
        if (notificaciones.isEmpty()) {
            return new ResponseEntity<>("No hay notificaciones para el pago con ID: " + pagoId, HttpStatus.OK);
        }
        return new ResponseEntity<>(notificaciones, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> crearNotificacion(@RequestBody Notificacion notificacion) {
        try {
            Notificacion nueva = notificacionService.guardarNotificacion(notificacion);
            return new ResponseEntity<>(nueva, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarNotificacion(@PathVariable Long id, @RequestBody Notificacion notificacion) {
        try {
            Notificacion actualizada = notificacionService.actualizarNotificacion(id, notificacion);
            return new ResponseEntity<>(actualizada, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarNotificacion(@PathVariable Long id) {
        try {
            notificacionService.eliminarNotificacion(id);
            return new ResponseEntity<>("Notificación eliminada exitosamente.", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
