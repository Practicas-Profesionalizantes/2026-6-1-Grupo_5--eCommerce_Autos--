package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.VisitasAuto;
import com.back.carfind.repository.VisitasAutoRepository;

@Service
public class VisitasAutoService {

    @Autowired
    private VisitasAutoRepository repository;

    public VisitasAuto crearVisitaAuto(VisitasAuto visitaAuto) {
        return repository.save(visitaAuto);
    }

    public VisitasAuto obtenerVisitaAuto(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Visita de auto no encontrada"
                ));
    }

    public List<VisitasAuto> listarVisitasAuto() {
        return repository.findAll();
    }

    public VisitasAuto actualizarVisitaAuto(
            Long id,
            VisitasAuto datos) {

        VisitasAuto visitaAuto = obtenerVisitaAuto(id);

        visitaAuto.setId_auto(datos.getId_auto());
        visitaAuto.setId_comprador(
                datos.getId_comprador()
        );
        visitaAuto.setFecha(datos.getFecha());
        visitaAuto.setResultado(
                datos.getResultado()
        );

        return repository.save(visitaAuto);
    }

    public void eliminarVisitaAuto(Long id) {
        VisitasAuto visitaAuto = obtenerVisitaAuto(id);

        repository.delete(visitaAuto);
    }
}