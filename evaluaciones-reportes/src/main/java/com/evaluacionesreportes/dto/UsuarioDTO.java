package com.evaluacionesreportes.dto;

import lombok.Data;

@Data
public class UsuarioDTO {
    private int id;
    private String rut;
    private String nombre;
    private String tipoUsuario;
    private String password;
}
