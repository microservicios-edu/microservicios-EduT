package com.matriculainscripcion.matricula_inscripcion_api.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
//Genera automáticamente métodos getters, setters, toString(), equals(), y hashCode() para todos los campos de la clase.
@AllArgsConstructor
//Genera un constructor con todos los atributos de la clase como parámetros.
@NoArgsConstructor
//Crea un constructor vacío sin parámetros. Es obligatorio para que JPA (Java Persistence API) pueda instanciar entidades desde la base de datos.
@Entity
//Declara una clase como una entidad persistente, es decir, una tabla en la base de datos.
//Esto permite que Spring Data JPA gestione automáticamente operaciones CRUD (insert, update, delete, select).
@Table(name = "matricula")

public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String usuario;
    private String password;
}
