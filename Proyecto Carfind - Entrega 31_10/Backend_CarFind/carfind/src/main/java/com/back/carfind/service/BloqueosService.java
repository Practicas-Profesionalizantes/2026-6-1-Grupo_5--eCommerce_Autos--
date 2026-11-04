package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.Bloqueos;
import com.back.carfind.model.BloqueosId;
import com.back.carfind.repository.BloqueosRepository;

@Service
public class BloqueosService {

    @Autowired
    private BloqueosRepository repository;

    public Bloqueos crearBloqueo(Bloqueos bloqueo) {
        return repository.save(bloqueo);
    }

    public Bloqueos obtenerBloqueo(Long usuarioId, Long usuarioBloqueadoId) {
        return repository.findById(new BloqueosId(usuarioId, usuarioBloqueadoId))
                .orElseThrow(() -> new RuntimeException("Bloqueo no encontrado"));
    }

    public List<Bloqueos> listarBloqueos() {
        return repository.findAll();
    }

    public void eliminarBloqueo(Long usuarioId, Long usuarioBloqueadoId) {
        Bloqueos bloqueo = obtenerBloqueo(usuarioId, usuarioBloqueadoId);
        repository.delete(bloqueo);
    }
}
