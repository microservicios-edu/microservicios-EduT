package com.evaluacionesreportes.controller;

import com.evaluacionesreportes.model.Evaluacion;
import com.evaluacionesreportes.service.EvaluacionService;

import java.util.List;
import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/evaluaciones")
public class EvaluacionController {

    @Autowired
    private EvaluacionService evaluacionService;

    @GetMapping
    public ResponseEntity<?> obtenerTodas() {
        List<Evaluacion> lista = evaluacionService.obtenerTodas();
        if (lista.isEmpty()) {
            Map<String, String> mensaje = Collections.singletonMap("mensaje", "No hay evaluaciones registradas");
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        }
        Map<String, Object> respuesta = Collections.singletonMap("evaluaciones", lista);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        try {
            Evaluacion evaluacion = evaluacionService.obtenerPorId(id);
            return new ResponseEntity<>(evaluacion, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(Collections.singletonMap("mensaje", e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody(required = false) Evaluacion evaluacion) {
        if (evaluacion == null) {
            return new ResponseEntity<>(Collections.singletonMap("mensaje", "No hay datos para agregar."), HttpStatus.BAD_REQUEST);
        }
        try {
            Evaluacion nuevo = evaluacionService.crear(evaluacion);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("mensaje", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody(required = false) Evaluacion evaluacion) {
        if (evaluacion == null) {
            return new ResponseEntity<>(Collections.singletonMap("mensaje", "No hay datos para actualizar."), HttpStatus.BAD_REQUEST);
        }
        try {
            Evaluacion actualizada = evaluacionService.actualizar(id, evaluacion);
            return new ResponseEntity<>(actualizada, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(Collections.singletonMap("mensaje", e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (!evaluacionService.existePorId(id)) {
            return new ResponseEntity<>(Collections.singletonMap("mensaje", "No se encontró ninguna evaluación con ID: " + id), HttpStatus.NOT_FOUND);
        }
        try {
            evaluacionService.eliminar(id);
            return new ResponseEntity<>(Collections.singletonMap("mensaje", "Evaluación eliminada exitosamente"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Collections.singletonMap("mensaje", "No se pudo eliminar la evaluación con ID: " + id), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}