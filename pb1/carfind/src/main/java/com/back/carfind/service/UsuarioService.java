package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.Usuario;
import com.back.carfind.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario crearUsuario(Usuario usuario) {
        return repository.save(usuario);
    }

    public Usuario obtenerUsuario(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Usuario no encontrado"
                ));
    }

    public List<Usuario> listarUsuarios() {
        return repository.findAll();
    }

    public Usuario actualizarUsuario(
            Long id,
            Usuario datos) {

        Usuario usuario = obtenerUsuario(id);

        usuario.setNombre(datos.getNombre());
        usuario.setEmail(datos.getEmail());
        usuario.setDni(datos.getDni());
        usuario.setPassword(datos.getPassword());
        usuario.setTelefono(datos.getTelefono());
        usuario.setReputacion(datos.getReputacion());
        usuario.setScore_riesgo(
                datos.getScore_riesgo()
        );
        usuario.setVerificado(datos.getVerificado());
        usuario.setFecha_creacion(
                datos.getFecha_creacion()
        );
        usuario.setTotal_compras(
                datos.getTotal_compras()
        );
        usuario.setTotal_ventas(
                datos.getTotal_ventas()
        );

        return repository.save(usuario);
    }

    public void eliminarUsuario(Long id) {
        Usuario usuario = obtenerUsuario(id);

        repository.delete(usuario);
    }
}