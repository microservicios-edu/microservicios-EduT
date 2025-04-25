package com.example.edutech.controller;

import com.example.edutech.model.Usuario;
import com.example.edutech.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Método para listar todos los usuarios
    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.getUsuarios();
    }

    // Método para agregar un nuevo usuario
    @PostMapping
    public Usuario agregarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.saveUsuario(usuario);
    }

    // Método para actualizar un usuario existente
    @PutMapping("/{id}")
    public Usuario actualizarUsuario(@PathVariable int id, @RequestBody Usuario usuarioActualizado) {
        // **Corrección:** Se añadió la línea para setear el ID del usuario actualizado
        // **Error:** Antes no se estaba seteando el ID del usuario actualizado, lo que causaba que la actualización no se realizara correctamente.
        usuarioActualizado.setId(id); 
        return usuarioService.updateUsuario(usuarioActualizado);
    }

    // Método para eliminar un usuario
    @DeleteMapping("/{id}")
    public String eliminarUsuario(@PathVariable int id) {
        // **Corrección:** Se mejoró la respuesta para que sea más informativa
        // **Error:** El código original devolvía una respuesta genérica que no indicaba si la eliminación fue exitosa.
        if (usuarioService.deleteUsuario(id)) {
            return "Usuario eliminado";
        } else {
            return "Usuario no encontrado";
        }
    }
}

