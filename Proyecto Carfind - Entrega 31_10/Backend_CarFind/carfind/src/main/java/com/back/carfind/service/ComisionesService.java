package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.Comisiones;
import com.back.carfind.repository.ComisionesRepository;

@Service
public class ComisionesService {

    @Autowired
    private ComisionesRepository repository;

    public Comisiones crearComision(Comisiones comision) {
        return repository.save(comision);
    }

    public Comisiones obtenerComision(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comision no encontrada"));
    }

    public List<Comisiones> listarComisiones() {
        return repository.findAll();
    }

    public Comisiones actualizarComision(Long id, Comisiones datos) {
        Comisiones comision = obtenerComision(id);
        comision.setCompraId(datos.getCompraId());
        comision.setPorcentaje(datos.getPorcentaje());
        comision.setMonto(datos.getMonto());
        return repository.save(comision);
    }

    public void eliminarComision(Long id) {
        Comisiones comision = obtenerComision(id);
        repository.delete(comision);
    }
}
