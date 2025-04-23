package com.example.recursoseducativos.controller;

import com.example.recursoseducativos.model.Libro;
import com.example.recursoseducativos.service.LibroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/libros")
public class LibroController {
    @Autowired
    private LibroService libroService;

    @GetMapping
    public List<Libro> listarLibros() {
        return libroService.getLibros();
    }

    @PostMapping
    public Libro agregarLibro(@RequestBody Libro libro) {
        return libroService.saveLibro (libro);
    }

    @GetMapping("{id}")
    public Libro buscarLibro(@PathVariable int id) {
        return libroService.getLibrold(id);
    }

    @PutMapping("{id}")
    public Libro actualizarLibro (@PathVariable int id, @RequestBody Libro libro){
        // el id lo usaremos mas adelante www
        return libroService.updateLibro (libro);
    }

    @DeleteMapping("{id}")
    public String eliminarLibro(@PathVariable int id) {
        return libroService.deleteLibro (id);
    }
}