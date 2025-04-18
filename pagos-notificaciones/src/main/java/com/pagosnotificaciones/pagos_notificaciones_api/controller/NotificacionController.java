package com.pagosnotificaciones.pagos_notificaciones_api.controller;

import com.pagosnotificaciones.pagos_notificaciones_api.model.Notificacion;
import com.pagosnotificaciones.pagos_notificaciones_api.service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    private final NotificacionService notificacionService;

    @Autowired
    public NotificacionController(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    // Obtener todas las notificaciones
    @GetMapping
    public List<Notificacion> listarNotificaciones() {
        return notificacionService.listarNotificaciones();
    }

    // Crear una nueva notificación
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Notificacion agregarNotificacion(@RequestBody Notificacion notificacion) {
        return notificacionService.agregarNotificacion(notificacion);
    }

    // Actualizar una notificación existente
    @PutMapping("/{id}")
    public Notificacion actualizarNotificacion(@PathVariable Long id, @RequestBody Notificacion nuevaNotificacion) {
        return notificacionService.actualizarNotificacion(id, nuevaNotificacion);
    }

    // Eliminar una notificación
    @DeleteMapping("/{id}")
    public String eliminarNotificacion(@PathVariable Long id) {
        if (notificacionService.eliminarNotificacion(id)) {
            return "Notificación con ID " + id + " eliminada.";
        } else {
            return "No se encontró la notificación con ID " + id;
        }
    }
}
