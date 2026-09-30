package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.Encuentros;
import com.back.carfind.repository.EncuentrosRepository;

@Service
public class EncuentrosService {

    @Autowired
    private EncuentrosRepository repository;

    public Encuentros crearEncuentro(Encuentros encuentro) {
        return repository.save(encuentro);
    }

    public Encuentros obtenerEncuentro(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Encuentro no encontrado"
                ));
    }

    public List<Encuentros> listarEncuentros() {
        return repository.findAll();
    }

    public Encuentros actualizarEncuentro(
            Long id,
            Encuentros datos) {

        Encuentros encuentro = obtenerEncuentro(id);

        encuentro.setId_compra(datos.getId_compra());
        encuentro.setId_vendedor(datos.getId_vendedor());
        encuentro.setId_comprador(datos.getId_comprador());
        encuentro.setFecha_programada(datos.getFecha_programada());
        encuentro.setLugar(datos.getLugar());
        encuentro.setEstado(datos.getEstado());
        encuentro.setNotas(datos.getNotas());
        encuentro.setFecha_creacion(datos.getFecha_creacion());

        return repository.save(encuentro);
    }

    public void eliminarEncuentro(Long id) {
        Encuentros encuentro = obtenerEncuentro(id);
        repository.delete(encuentro);
    }
}