package com.example.edutech.services;

import com.example.edutech.model.Usuario;
import com.example.edutech.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public List<Usuario> getUsuarios() {
        return usuarioRepositorio.findAll();
    }

    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

    public Usuario getUsuarioId(int id) {
        Optional<Usuario> optional = usuarioRepositorio.findById(id);
        return optional.orElse(null);
    }

    public Usuario updateUsuario(Usuario usuario){
        return usuarioRepositorio.save(usuario);
    }

    public boolean deleteUsuario(int id) {
        if (usuarioRepositorio.existsById(id)) {
            usuarioRepositorio.deleteById(id);
            return true;
        }
        return false;
    }

    public Usuario getUsuarioPorRut(String rut) {
        Optional<Usuario> optional = usuarioRepositorio.findByRut(rut);
        return optional.orElse(null);
    }

}
