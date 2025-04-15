package com.pagosnotificaciones.pagos_notificaciones_api.controller;

import org.springframework.web.bind.annotation.*;

import com.pagosnotificaciones.pagos_notificaciones_api.model.Notificacion;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    private List<Notificacion> notificaciones = new ArrayList<>();

    @GetMapping
    public List<Notificacion> listarNotificaciones() {
        return notificaciones;
    }

    @PostMapping
    public Notificacion agregarNotificacion(@RequestBody Notificacion notificacion) {
        notificaciones.add(notificacion);
        return notificacion;
    }

    @PutMapping("/{id}")
    public Notificacion actualizarNotificacion(@PathVariable String id, @RequestBody Notificacion nueva) {
        for (Notificacion n : notificaciones) {
            if (n.getId().equals(id)) {
                n.setMensaje(nueva.getMensaje());
                n.setDestinatario(nueva.getDestinatario());
                return n;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminarNotificacion(@PathVariable String id) {
        notificaciones.removeIf(n -> n.getId().equals(id));
        return "Notificación con ID " + id + " eliminada.";
    }
}
