package com.pagosnotificaciones.pagos_notificaciones_api.controller;

import com.pagosnotificaciones.pagos_notificaciones_api.model.Notificacion;
import com.pagosnotificaciones.pagos_notificaciones_api.service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;

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

    @GetMapping
    public List<Notificacion> listarNotificaciones() {
        return notificacionService.listarNotificaciones();
    }

    @PostMapping
    public Notificacion agregarNotificacion(@RequestBody Notificacion notificacion) {
        return notificacionService.agregarNotificacion(notificacion);
    }

    @PutMapping("/{id}")
    public Notificacion actualizarNotificacion(@PathVariable Long id, @RequestBody Notificacion nuevaNotificacion) {
        return notificacionService.actualizarNotificacion(id, nuevaNotificacion);
    }

    @DeleteMapping("/{id}")
    public String eliminarNotificacion(@PathVariable Long id) {
        if (notificacionService.eliminarNotificacion(id)) {
            return "Notificación eliminada con ID: " + id;
        }
        return "No se encontró notificación con ID: " + id;
    }
}
