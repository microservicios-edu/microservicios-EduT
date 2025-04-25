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
        // **Corrección:** Se añadió un retorno de null si no se encuentra el usuario a actualizar.
        // **Error:** Si no se encontraba el usuario a actualizar, no se devolvía ningún valor, lo que causaba una inconsistencia.
        return usuarioRepositorio.actualizar(usuario);
    }

    // Método para eliminar un usuario
    public boolean deleteUsuario(int id) {
        // **Corrección:** Se cambió la respuesta a un booleano para indicar si la eliminación fue exitosa.
        // **Error:** El método anterior no retornaba nada, lo que dificultaba saber si el usuario fue realmente eliminado.
        return usuarioRepositorio.eliminar(id);
    }
}
