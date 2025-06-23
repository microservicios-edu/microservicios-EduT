package com.matriculainscripcion.matricula_inscripcion_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    //Para inyectar RestTemplate y hacer llamadas HTTP
}
