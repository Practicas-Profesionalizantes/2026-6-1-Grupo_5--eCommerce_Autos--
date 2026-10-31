package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.PlanesCuotas;
import com.back.carfind.repository.PlanesCuotasRepository;

@Service
public class PlanesCuotasService {

    @Autowired
    private PlanesCuotasRepository repository;

    public PlanesCuotas crearPlanCuotas(PlanesCuotas planCuotas) {
        return repository.save(planCuotas);
    }

    public PlanesCuotas obtenerPlanCuotas(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Plan de cuotas no encontrado"
                ));
    }

    public List<PlanesCuotas> listarPlanesCuotas() {
        return repository.findAll();
    }

    public PlanesCuotas actualizarPlanCuotas(
            Long id,
            PlanesCuotas datos) {

        PlanesCuotas planCuotas = obtenerPlanCuotas(id);

        planCuotas.setCantidad_cuotas(
                datos.getCantidad_cuotas()
        );
        planCuotas.setInteres(datos.getInteres());
        planCuotas.setDescripcion(
                datos.getDescripcion()
        );

        return repository.save(planCuotas);
    }

    public void eliminarPlanCuotas(Long id) {
        PlanesCuotas planCuotas = obtenerPlanCuotas(id);

        repository.delete(planCuotas);
    }
}