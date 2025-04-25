package com.example.edutech.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Esta es la estructura a ocupar para utilizar Lombok en las clases de modelo.
// Lombok nos permite reducir código repetitivo, como getters, setters, constructores y otros métodos

@Data // Genera automáticamente los métodos getter, setter, toString(), hashCode(), equals() y otros.
@AllArgsConstructor // Genera un constructor con todos los campos.
@NoArgsConstructor // Genera un constructor vacío.

public class Usuario {
    private int id;
    private String nombre;
    private int edad;
}

