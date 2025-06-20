package com.recursos_educativos.cl.recursos_educativos.controller;

import com.recursos_educativos.cl.recursos_educativos.model.Libro;
import com.recursos_educativos.cl.recursos_educativos.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @GetMapping
    public ResponseEntity<List<Libro>> listar() {
        List<Libro> libros = libroService.findAll();
        if (libros.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(libros);
    }

    @PostMapping
    public ResponseEntity<Libro> guardar(@RequestBody Libro libro) {
        return ResponseEntity.status(201).body(libroService.save(libro));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> buscar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(libroService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
public ResponseEntity<Libro> actualizar(@PathVariable Long id, @RequestBody Libro libro) {
    try {
        // No es necesario obtener el libro "existente" aquí, solo actualizamos el libro directamente.
        libro.setId(id);  // Establecemos el ID que viene en la URL.
        return ResponseEntity.ok(libroService.save(libro));  // Guardamos el libro actualizado.
    } catch (Exception e) {
        return ResponseEntity.notFound().build();  // Si hay algún error (como que no se encontró el libro), respondemos con un 404.
    }
}


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            libroService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
