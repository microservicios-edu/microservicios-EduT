package com.example.edutech.repository;

import com.example.edutech.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepositorio {

    // Lista que actúa como base de datos temporal
    private List<Usuario> listaUsuarios = new ArrayList<>();

    // Método para obtener todos los usuarios
    public List<Usuario> obtenerUsuarios() {
        return listaUsuarios;
    }

    // Método para buscar un usuario por su ID
    public Usuario buscarPorId(int id) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        return null; // Si no se encuentra, retorna null
    }

    // Método para guardar un usuario
    public Usuario guardar(Usuario usuario) {
        listaUsuarios.add(usuario);
        return usuario;
    }

    // Método para actualizar un usuario
    public Usuario actualizar(Usuario usuario) {
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getId() == usuario.getId()) {
                listaUsuarios.set(i, usuario);
                return usuario;
            }
        }
        return null; // Si no se encuentra el usuario, retorna null
    }

    // Método para eliminar un usuario
    public boolean eliminar(int id) {
        Usuario usuario = buscarPorId(id);
        if (usuario != null) {
            listaUsuarios.remove(usuario);
            return true; // El usuario fue eliminado
        }
        return false; // Si no se encontró, retorna false
    }
}

