package com.soporte.soporte_api.repository;

import com.soporte.soporte_api.model.Soporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoporteRepository extends JpaRepository<Soporte, Integer> {
    // Puedes agregar métodos personalizados si necesitas, pero no es obligatorio
}

