package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.Verificaciones;
import com.back.carfind.repository.VerificacionesRepository;

@Service
public class VerificacionesService {

    @Autowired
    private VerificacionesRepository repository;

    public Verificaciones crearVerificacion(
            Verificaciones verificacion) {

        return repository.save(verificacion);
    }

    public Verificaciones obtenerVerificacion(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Verificación no encontrada"
                ));
    }

    public List<Verificaciones> listarVerificaciones() {
        return repository.findAll();
    }

    public Verificaciones actualizarVerificacion(
            Long id,
            Verificaciones datos) {

        Verificaciones verificacion =
                obtenerVerificacion(id);

        verificacion.setId_usuario(
                datos.getId_usuario()
        );

        verificacion.setTipo_verificacion(
                datos.getTipo_verificacion()
        );

        verificacion.setEstado_verificacion(
                datos.getEstado_verificacion()
        );

        verificacion.setFecha_verificaion(
                datos.getFecha_verificaion()
        );

        return repository.save(verificacion);
    }

    public void eliminarVerificacion(Long id) {
        Verificaciones verificacion =
                obtenerVerificacion(id);

        repository.delete(verificacion);
    }
}