package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.Sesiones;
import com.back.carfind.repository.SesionesRepository;

@Service
public class SesionesService {

    @Autowired
    private SesionesRepository repository;

    public Sesiones crearSesion(Sesiones sesion) {
        return repository.save(sesion);
    }

    public Sesiones obtenerSesion(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Sesión no encontrada"
                ));
    }

    public List<Sesiones> listarSesiones() {
        return repository.findAll();
    }

    public Sesiones actualizarSesion(
            Long id,
            Sesiones datos) {

        Sesiones sesion = obtenerSesion(id);

        sesion.setId_usuario(datos.getId_usuario());
        sesion.setToken(datos.getToken());
        sesion.setFecha_expiracion(
                datos.getFecha_expiracion()
        );

        return repository.save(sesion);
    }

    public void eliminarSesion(Long id) {
        Sesiones sesion = obtenerSesion(id);

        repository.delete(sesion);
    }
}