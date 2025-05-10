package com.soporte.soporte_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.soporte.soporte_api.model.Soporte;
import com.soporte.soporte_api.service.SoporteService;

@RestController
@RequestMapping("/api/v1/soporte")
public class SoporteController {

    @Autowired
    private SoporteService soporteService;

    @GetMapping
    public List<Soporte> listarSoporte() {
        return soporteService.getSoportes();
    }

    @PostMapping
    public Soporte agregarSoporte(@RequestBody Soporte soporte) {
        return soporteService.saveSoporte(soporte);
    }

    @GetMapping("{id}")
    public Soporte buscarSoporte(@PathVariable int id) {
        return soporteService.getSoporteId(id);
    }

    @PutMapping("{id}")
    public Soporte actualizarSoporte(@PathVariable int id, @RequestBody Soporte soporte) {
        soporte.setId(id); // Esto es clave para evitar errores
        return soporteService.updateSoporte(soporte);
    }
}

