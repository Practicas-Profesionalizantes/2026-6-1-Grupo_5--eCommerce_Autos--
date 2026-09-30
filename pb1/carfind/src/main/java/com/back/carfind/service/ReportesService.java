package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.Reportes;
import com.back.carfind.repository.ReportesRepository;

@Service
public class ReportesService {

    @Autowired
    private ReportesRepository repository;

    public Reportes crearReporte(Reportes reporte) {
        return repository.save(reporte);
    }

    public Reportes obtenerReporte(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Reporte no encontrado"
                ));
    }

    public List<Reportes> listarReportes() {
        return repository.findAll();
    }

    public Reportes actualizarReporte(
            Long id,
            Reportes datos) {

        Reportes reporte = obtenerReporte(id);

        reporte.setId_usuario(datos.getId_usuario());
        reporte.setId_publicacion(
                datos.getId_publicacion()
        );
        reporte.setMotivo(datos.getMotivo());
        reporte.setDescripcion(
                datos.getDescripcion()
        );
        reporte.setEstado(datos.getEstado());
        reporte.setFecha_reporte(
                datos.getFecha_reporte()
        );

        return repository.save(reporte);
    }

    public void eliminarReporte(Long id) {
        Reportes reporte = obtenerReporte(id);

        repository.delete(reporte);
    }
}