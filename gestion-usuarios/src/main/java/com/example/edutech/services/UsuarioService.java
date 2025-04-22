package com.example.edutech.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.edutech.repository.UsuarioRepositorio;
import com.example.edutech.model.*;
import java.util.List;


@Service
public class UsuarioService {

@Autowired
private UsuarioRepositorio usuarioRepositorio;

    public List<Usuario> getUsuarios(){
        return usuarioRepositorio.obtenerUsuarios();
    }

    public Usuario saveUsuario(Usuario usuario){
        return usuarioRepositorio.guardar(usuario);
    }

    public Usuario getUsuarioId(int id){
        return usuarioRepositorio.buscarPorId(id);
    }

    public Usuario updateUsuario(Usuario usuario){
        return usuarioRepositorio.actualizar(usuario);
    }

    public String deleteUsuario(int id){
        usuarioRepositorio.eliminar(id);
        return "usuario eliminado";
    }

}
