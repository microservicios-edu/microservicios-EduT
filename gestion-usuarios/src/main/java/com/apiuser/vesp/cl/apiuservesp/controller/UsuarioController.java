package com.apiuser.vesp.cl.apiuservesp.controller;

import org.springframework.web.bind.annotation.*;



import java.util.ArrayList;

import java.util.List;



@RestController

@RequestMapping("/api/usuarios")

public class UsuarioController {



  private List<Usuario> usuarios = new ArrayList<>();



  @GetMapping

  public List<Usuario> listarUsuarios() {

    return usuarios;

  }



  @PostMapping

  public Usuario agregarUsuario(@RequestBody Usuario usuario) {

    usuarios.add(usuario);

    return usuario;

  }
  
  @PutMapping("/{id}")
public Usuario actualizarUsuario(@PathVariable int id, @RequestBody Usuario usuarioActualizado) {
    for (int i = 0; i < usuarios.size(); i++) {
        Usuario u = usuarios.get(i);
        if (u.getId() == id) {
            usuarios.set(i, usuarioActualizado);
            return usuarioActualizado;
        }
    }
    throw new RuntimeException("Usuario no encontrado con ID: " + id);
}

@DeleteMapping("/{id}")
public String eliminarUsuario(@PathVariable int id) {
    for (int i = 0; i < usuarios.size(); i++) {
        if (usuarios.get(i).getId() == id) {
            usuarios.remove(i);
            return "Usuario eliminado con ID: " + id;
        }
    }
    return "Usuario no encontrado con ID: " + id;
}

  
}