package com.matriculainscripcion.matricula_inscripcion_api.client;

import com.example.edutech.dto.RecursoEducativoDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

public class RecursoEducativoClient { //Consumir otros microservicios

    private final RestTemplate restTemplate;

    public RecursoEducativoClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public RecursoEducativoDTO obtenerRecursoPorId(Long id) {
        String url = "http://localhost:8082/api/v1/recursos/" + id; // Cambia el puerto y path según tu servicio
        return restTemplate.getForObject(url, RecursoEducativoDTO.class);
    }

}
