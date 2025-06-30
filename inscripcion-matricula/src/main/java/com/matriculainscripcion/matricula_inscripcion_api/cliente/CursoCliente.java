package com.matriculainscripcion.matricula_inscripcion_api.cliente;

import com.matriculainscripcion.matricula_inscripcion_api.dto.CursoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

//cliente se encargará de hacer las peticiones al microservicio recursos-educativos.

@Component
public class CursoCliente {

    @Autowired
    private RestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8082/api/v1/cursos"; 

    public List<CursoDTO> obtenerCursos() {
        CursoDTO[] cursos = restTemplate.getForObject(BASE_URL, CursoDTO[].class);
        return Arrays.asList(cursos);
    }

    public CursoDTO obtenerCursoPorId(Long id) {
        return restTemplate.getForObject(BASE_URL + "/" + id, CursoDTO.class);
    }

}
