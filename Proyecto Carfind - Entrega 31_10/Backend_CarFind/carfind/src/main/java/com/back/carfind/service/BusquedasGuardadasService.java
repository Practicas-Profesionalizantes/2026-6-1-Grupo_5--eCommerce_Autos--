package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.BusquedasGuardadas;
import com.back.carfind.repository.BusquedasGuardadasRepository;

@Service
public class BusquedasGuardadasService {

    @Autowired
    private BusquedasGuardadasRepository repository;

    public BusquedasGuardadas crearBusquedaGuardada(BusquedasGuardadas busquedaGuardada) {
        return repository.save(busquedaGuardada);
    }

    public BusquedasGuardadas obtenerBusquedaGuardada(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Busqueda guardada no encontrada"));
    }

    public List<BusquedasGuardadas> listarBusquedasGuardadas() {
        return repository.findAll();
    }

    public BusquedasGuardadas actualizarBusquedaGuardada(Long id, BusquedasGuardadas datos) {
        BusquedasGuardadas busquedaGuardada = obtenerBusquedaGuardada(id);
        busquedaGuardada.setUsuarioId(datos.getUsuarioId());
        busquedaGuardada.setQuery(datos.getQuery());
        busquedaGuardada.setFecha(datos.getFecha());
        return repository.save(busquedaGuardada);
    }

    public void eliminarBusquedaGuardada(Long id) {
        BusquedasGuardadas busquedaGuardada = obtenerBusquedaGuardada(id);
        repository.delete(busquedaGuardada);
    }
}
