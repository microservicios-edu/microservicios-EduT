package com.example.edutech.model;

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
@Table(name = "usuario")
//Indica el nombre específico de la tabla en la base de datos a la que se va a mapear la clase.

public class Usuario {
    //atributos
    @Id
    //Marca un campo como la clave primaria (Primary Key) de la tabla.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Indica que el valor del campo se genera automáticamente en la base de datos al insertar un nuevo registro.
    private int id;
    private String nombre;
    private String rut;
}

