package com.example.edutech.services;

import com.example.edutech.model.Usuario;
import com.example.edutech.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    // Método para obtener todos los usuarios
    public List<Usuario> getUsuarios() {
        return usuarioRepositorio.obtenerUsuarios();
    }

    // Método para guardar un usuario
    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepositorio.guardar(usuario);
    }

    // Método para obtener un usuario por su ID
    public Usuario getUsuarioId(int id) {
        return usuarioRepositorio.buscarPorId(id);
    }

    // Método para actualizar un usuario
    public Usuario updateUsuario(Usuario usuario) {
        return usuarioRepositorio.actualizar(usuario);
    }

    // Método para eliminar un usuario
    public boolean deleteUsuario(int id) {
        return usuarioRepositorio.eliminar(id);
    }
}
