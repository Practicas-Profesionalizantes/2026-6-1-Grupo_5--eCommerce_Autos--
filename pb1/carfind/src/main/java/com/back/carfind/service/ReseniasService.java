package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.Resenias;
import com.back.carfind.repository.ReseniasRepository;

@Service
public class ReseniasService {

    @Autowired
    private ReseniasRepository repository;

    public Resenias crearResenia(Resenias resenia) {
        return repository.save(resenia);
    }

    public Resenias obtenerResenia(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Reseña no encontrada"
                ));
    }

    public List<Resenias> listarResenias() {
        return repository.findAll();
    }

    public Resenias actualizarResenia(
            Long id,
            Resenias datos) {

        Resenias resenia = obtenerResenia(id);

        resenia.setId_comprador(
                datos.getId_comprador()
        );

        resenia.setId_vendedor(
                datos.getId_vendedor()
        );

        resenia.setPuntuacion(
                datos.getPuntuacion()
        );

        resenia.setComentario(
                datos.getComentario()
        );

        resenia.setFecha_resenia(
                datos.getFecha_resenia()
        );

        return repository.save(resenia);
    }

    public void eliminarResenia(Long id) {
        Resenias resenia = obtenerResenia(id);

        repository.delete(resenia);
    }
}