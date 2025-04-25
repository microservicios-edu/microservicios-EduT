package com.pagosnotificaciones.pagos_notificaciones_api.service;

import com.pagosnotificaciones.pagos_notificaciones_api.model.Notificacion;
import com.pagosnotificaciones.pagos_notificaciones_api.repository.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@Service
public class NotificacionService {

    private final NotificacionRepository notificacionRepository;
    private final PagoService pagoService;

    @Autowired
    public NotificacionService(NotificacionRepository notificacionRepository, PagoService pagoService) {
        this.notificacionRepository = notificacionRepository;
        this.pagoService = pagoService;
    }

    public List<Notificacion> listarNotificaciones() {
        return notificacionRepository.findAll();
    }

    public Notificacion agregarNotificacion(Notificacion notificacion) {
        // Verificamos si el pago existe
        if (!pagoService.existePago(notificacion.getPagoId())) {
            // Si el pago no existe, lanzamos una excepción con código 400 (Bad Request)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pago con ID " + notificacion.getPagoId() + " no encontrado.");
        }

        // Si el pago existe, guardamos la notificación
        return notificacionRepository.save(notificacion);
    }

    public Notificacion actualizarNotificacion(Long id, Notificacion nuevaNotificacion) {
        Optional<Notificacion> existente = notificacionRepository.findById(id);
        if (existente.isPresent()) {
            Notificacion n = existente.get();
            n.setMensaje(nuevaNotificacion.getMensaje());
            n.setTipo(nuevaNotificacion.getTipo());
            n.setPagoId(nuevaNotificacion.getPagoId());
            return notificacionRepository.save(n);
        }
        return null;
    }

    public boolean eliminarNotificacion(Long id) {
        return notificacionRepository.deleteById(id);
    }
}
