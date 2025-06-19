package com.recursos_educativos.cl.recursos_educativos.repository;

import com.recursos_educativos.cl.recursos_educativos.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByAutor(String autor);
    List<Libro> findByTituloContainingIgnoreCase(String titulo);
    List<Libro> findByGenero(String genero);
}
