package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.Transmisiones;
import com.back.carfind.repository.TransmisionesRepository;

@Service
public class TransmisionesService {

    @Autowired
    private TransmisionesRepository repository;

    public Transmisiones crearTransmision(
            Transmisiones transmision) {

        return repository.save(transmision);
    }

    public Transmisiones obtenerTransmision(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Transmisión no encontrada"
                ));
    }

    public List<Transmisiones> listarTransmisiones() {
        return repository.findAll();
    }

    public Transmisiones actualizarTransmision(
            Long id,
            Transmisiones datos) {

        Transmisiones transmision =
                obtenerTransmision(id);

        transmision.setNombre_transmision(
                datos.getNombre_transmision()
        );

        return repository.save(transmision);
    }

    public void eliminarTransmision(Long id) {
        Transmisiones transmision =
                obtenerTransmision(id);

        repository.delete(transmision);
    }
}