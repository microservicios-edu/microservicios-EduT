package com.evaluacionesreportes.controller;

import com.evaluacionesreportes.model.Reporte;
import com.evaluacionesreportes.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("api/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping
    public ResponseEntity<?> obtenerTodos() {
        List<Reporte> reportes = reporteService.obtenerTodos();
        if (reportes.isEmpty()) {
            Map<String, String> mensaje = Collections.singletonMap("mensaje", "No hay reportes registrados");
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        }
        Map<String, Object> respuesta = Collections.singletonMap("reportes", reportes);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        try {
            Reporte reporte = reporteService.obtenerPorId(id);
            return ResponseEntity.ok(reporte);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("mensaje", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody(required = false) Reporte reporte) {
        if (reporte == null) {
            return new ResponseEntity<>(Collections.singletonMap("mensaje", "No hay datos para agregar."), HttpStatus.BAD_REQUEST);
        }
        try {
            Reporte creado = reporteService.crear(reporte);
            return ResponseEntity.status(HttpStatus.CREATED).body(creado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("mensaje", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody(required = false) Reporte reporte) {
        if (reporte == null) {
            return new ResponseEntity<>(Collections.singletonMap("mensaje", "No hay datos para actualizar."), HttpStatus.BAD_REQUEST);
        }
        try {
            Reporte actualizado = reporteService.actualizar(id, reporte);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("mensaje", "No se encontró ningún reporte con ID: " + id));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (!reporteService.existePorId(id)) {
            return new ResponseEntity<>(Collections.singletonMap("mensaje", "No se encontró ningún reporte con ID: " + id), HttpStatus.NOT_FOUND);
        }
        try {
            reporteService.eliminar(id);
            return ResponseEntity.ok(Collections.singletonMap("mensaje", "Reporte eliminado correctamente con ID: " + id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("mensaje", "Error al eliminar el reporte con ID: " + id));
        }
    }
}