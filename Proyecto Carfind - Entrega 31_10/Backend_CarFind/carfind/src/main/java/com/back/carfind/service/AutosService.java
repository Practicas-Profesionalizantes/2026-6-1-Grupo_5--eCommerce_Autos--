package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.Autos;
import com.back.carfind.repository.AutosRepository;

@Service
public class AutosService {

    @Autowired
    private AutosRepository repository;

    public Autos crearAuto(Autos auto) {
        return repository.save(auto);
    }

    public Autos obtenerAuto(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Auto no encontrado"));
    }

    public List<Autos> listarAutos() {
        return repository.findAll();
    }

    public Autos actualizarAuto(Long id, Autos datos) {
        Autos auto = obtenerAuto(id);
        auto.setModeloId(datos.getModeloId());
        auto.setVersion(datos.getVersion());
        auto.setAnio(datos.getAnio());
        auto.setKms(datos.getKms());
        auto.setVtv(datos.getVtv());
        auto.setGrabado(datos.getGrabado());
        auto.setCombustibleId(datos.getCombustibleId());
        auto.setTransmisionId(datos.getTransmisionId());
        auto.setColor(datos.getColor());
        auto.setPuertas(datos.getPuertas());
        auto.setDescripcion(datos.getDescripcion());
        auto.setFechaCreacion(datos.getFechaCreacion());
        auto.setEliminado(datos.getEliminado());
        auto.setFechaEliminacion(datos.getFechaEliminacion());
        return repository.save(auto);
    }

    public void eliminarAuto(Long id) {
        Autos auto = obtenerAuto(id);
        repository.delete(auto);
    }
}
