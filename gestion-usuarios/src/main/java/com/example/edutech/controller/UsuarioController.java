package com.example.edutech.controller;

import com.example.edutech.model.Usuario;
import com.example.edutech.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
//import org.springframework.http.ResponseEntity;
import java.util.Map;


import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Método para listar todos los usuarios
    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.getUsuarios();
    }

    // Método para obtener un usuario por ID
    @GetMapping("/{id}")
    public Usuario obtenerUsuarioPorId(@PathVariable int id) {
        return usuarioService.getUsuarioId(id);
    }

    // Método para agregar un nuevo usuario
    @PostMapping
    public Usuario agregarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.saveUsuario(usuario);
    }

    // Método para actualizar un usuario existente
    @PutMapping("/{id}")
    public Usuario actualizarUsuario(@PathVariable int id, @RequestBody Usuario usuario){
        usuario.setId(id);
        return usuarioService.updateUsuario(usuario);
    }


    // Método para eliminar un usuario
    @DeleteMapping("/{id}")
    public String eliminarUsuario(@PathVariable int id) {
        
        if (usuarioService.deleteUsuario(id)) {
            return "Usuario eliminado";
        } else {
            return "Usuario no encontrado";
        }
    }

    // Método para buscar un usuario por RUT y cambiar clave
    @PutMapping("/cambiar-contrasena")
    public String cambiarContrasena(@RequestBody Map<String, String> datos) {
        String rut = datos.get("rut");
        String nuevaPassword = datos.get("password");

        Usuario usuario = usuarioService.getUsuarioPorRut(rut);

        if (usuario == null) {
            return "Usuario no encontrado";
        }

        usuario.setPassword(nuevaPassword);
        usuarioService.updateUsuario(usuario);
        return "Contraseña actualizada exitosamente";
    }

    
}

