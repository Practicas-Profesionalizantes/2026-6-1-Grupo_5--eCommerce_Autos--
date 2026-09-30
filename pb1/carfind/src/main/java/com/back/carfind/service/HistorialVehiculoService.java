package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.HistorialVehiculo;
import com.back.carfind.repository.HistorialVehiculoRepository;

@Service
public class HistorialVehiculoService {

    @Autowired
    private HistorialVehiculoRepository repository;

    public HistorialVehiculo crearHistorialVehiculo(
            HistorialVehiculo historialVehiculo) {

        return repository.save(historialVehiculo);
    }

    public HistorialVehiculo obtenerHistorialVehiculo(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Historial del vehículo no encontrado"
                ));
    }

    public List<HistorialVehiculo> listarHistorialesVehiculo() {
        return repository.findAll();
    }

    public HistorialVehiculo actualizarHistorialVehiculo(
            Long id,
            HistorialVehiculo datos) {

        HistorialVehiculo historialVehiculo =
                obtenerHistorialVehiculo(id);

        historialVehiculo.setId_auto(
                datos.getId_auto()
        );

        historialVehiculo.setTipo_historial(
                datos.getTipo_historial()
        );

        historialVehiculo.setDescripcion_historial(
                datos.getDescripcion_historial()
        );

        historialVehiculo.setFecha_historial(
                datos.getFecha_historial()
        );

        return repository.save(historialVehiculo);
    }

    public void eliminarHistorialVehiculo(Long id) {
        HistorialVehiculo historialVehiculo =
                obtenerHistorialVehiculo(id);

        repository.delete(historialVehiculo);
    }
}