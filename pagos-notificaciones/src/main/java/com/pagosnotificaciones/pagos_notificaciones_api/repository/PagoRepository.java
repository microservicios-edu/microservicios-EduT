package com.pagosnotificaciones.pagos_notificaciones_api.repository;

import com.pagosnotificaciones.pagos_notificaciones_api.model.Pago;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PagoRepository {

    private final List<Pago> pagos = new ArrayList<>();
    private Long currentId = 1L;

    public List<Pago> findAll() {
        System.out.println("Datos en la lista de pagos: " + pagos);  // Verificar si hay datos
        return pagos;
    }

    public Optional<Pago> findById(Long id) {
        return pagos.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public Pago save(Pago pago) {
        if (pago.getId() == null) {
            pago.setId(currentId++);
            pagos.add(pago);
            System.out.println("Pago guardado: " + pago);  // Verificar que se guarda el pago
        } else {
            pagos.replaceAll(p -> p.getId().equals(pago.getId()) ? pago : p);
        }
        return pago;
    }

    public boolean deleteById(Long id) {
        return pagos.removeIf(p -> p.getId().equals(id));
    }

    public boolean existsById(Long id) {
        return pagos.stream().anyMatch(p -> p.getId().equals(id));
    }
}
