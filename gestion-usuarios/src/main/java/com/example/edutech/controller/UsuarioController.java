package com.example.edutech.controller;

import com.example.edutech.model.Usuario;
import com.example.edutech.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import java.util.HashMap;
import org.springframework.http.HttpStatus;

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

    @GetMapping("/por-rut")
    public ResponseEntity<?> obtenerUsuarioCompletoPorRut(@RequestParam String rut) {
        Usuario usuario = usuarioService.getUsuarioPorRut(rut);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario no encontrado con RUT: " + rut);
        }

        return ResponseEntity.ok(usuario);
    }

    // Método para agregar un nuevo usuario
    @PostMapping
    public ResponseEntity<?> agregarUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario usuarioExistente = usuarioService.getUsuarioPorRut(usuario.getRut());
            if (usuarioExistente != null) {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body("Error: El RUT ya está registrado");
            }

            Usuario nuevoUsuario = usuarioService.saveUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al agregar usuario: " + e.getMessage());
        }
    }

    // Método para actualizar un usuario existente
    @PutMapping("/{id}")
    public Usuario actualizarUsuario(@PathVariable int id, @RequestBody Usuario usuario) {
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
    public Map<String, String> cambiarContrasena(@RequestBody Map<String, String> datos) {
        String rut = datos.get("rut");
        String password = datos.get("password");

        Map<String, String> response = new HashMap<>();

        Usuario usuario = usuarioService.getUsuarioPorRut(rut);

        if (usuario == null) {
            response.put("message", "Usuario no encontrado");
            return response;
        }

        usuario.setPassword(password);
        usuarioService.updateUsuario(usuario);

        response.put("message", "Contraseña actualizada exitosamente");
        return response;
    }

    // Método para autenticar a un usuario
    @GetMapping("/login") // devolverá un archivo json con la respuesta
    public Map<String, String> login(@RequestParam String rut, @RequestParam String clave) {
        Usuario usuario = usuarioService.getUsuarioPorRut(rut);
        Map<String, String> response = new HashMap<>(); // ocupamos Map en la firma del método y HasMap como
                                                        // implementación

        if (usuario == null) {
            response.put("message", "Usuario no encontrado");
            return response;
        }

        if (!usuario.getPassword().equals(clave)) {
            response.put("message", "Clave incorrecta");
            return response;
        }

        response.put("message", "Autenticación exitosa");
        response.put("tipoUsuario", usuario.getTipoUsuario());
        response.put("rut", usuario.getRut());
        response.put("nombre", usuario.getNombre());
        return response;
    }

    // Método para obtener rut y nombre de un usuario autenticado
    @GetMapping("/datos-usuario")
    public Map<String, String> obtenerDatosUsuario(@RequestParam String rut) {
        Usuario usuario = usuarioService.getUsuarioPorRut(rut);
        Map<String, String> response = new HashMap<>();

        if (usuario == null) {
            response.put("message", "Usuario no encontrado");
            return response;
        }

        response.put("rut", usuario.getRut());
        response.put("nombre", usuario.getNombre());
        return response;
    }

}
