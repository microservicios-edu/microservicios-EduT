package com.recursos_educativos.cl.recursos_educativos.service;

import com.recursos_educativos.cl.recursos_educativos.model.Libro;
import com.recursos_educativos.cl.recursos_educativos.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    public List<Libro> findAll() {
        return libroRepository.findAll();
    }

    public Libro findById(Long id) {
        return libroRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Libro no encontrado con id: " + id));
    }

    public Libro save(Libro libro) {
        return libroRepository.save(libro);
    }

    public void delete(Long id) {
        if (!libroRepository.existsById(id)) {
            throw new NoSuchElementException("No se puede eliminar. Libro no encontrado con id: " + id);
        }
        libroRepository.deleteById(id);
    }
}
