package com.evaluacionesreportes.client;

import com.evaluacionesreportes.dto.UsuarioDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UsuarioClient {

    private final WebClient webClient;

    public UsuarioClient(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://localhost:8081/api/v1/usuarios").build(); // Cambia el puerto si usas
                                                                                           // otro
    }

    public Mono<UsuarioDTO> obtenerUsuarioPorId(int id) {
        return webClient
                .get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(UsuarioDTO.class);
    }

    public Mono<UsuarioDTO> obtenerUsuarioPorRut(String rut) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/por-rut")
                        .queryParam("rut", rut)
                        .build())
                .retrieve()
                .bodyToMono(UsuarioDTO.class);
    }

}
