package com.example.edutech.repository;

import com.example.edutech.model.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepositorio {

    //arreglo que guarda los usuarios
    private List<Usuario> listaUsuarios = new ArrayList<>();

    //Método para retornar todos los usuarios
    public List<Usuario> obtenerUsuarios() {
        return listaUsuarios;
    }
    //Buscar un usuario por ID
    public Usuario buscarPorId(int id) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        return null;
    }
    //Guardar un usuario
    public Usuario guardar(Usuario usuar) {
        listaUsuarios.add(usuar);
        return usuar;
    }
    //Actualizar un usuario
    public Usuario actualizar(Usuario usuar) {
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getId() == usuar.getId()) {
                listaUsuarios.set(i, usuar);
                return usuar;
            }
        }
        return null; // Si no se encontró el usuario
    }
    //Eliminar un usuario
    public void eliminar(int id) {
        Usuario usuario = buscarPorId(id);
        if (usuario != null) {
            listaUsuarios.remove(usuario);
        }
    }
}