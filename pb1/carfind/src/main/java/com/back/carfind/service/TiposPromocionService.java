package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.TiposPromocion;
import com.back.carfind.repository.TiposPromocionRepository;

@Service
public class TiposPromocionService {

    @Autowired
    private TiposPromocionRepository repository;

    public TiposPromocion crearTipoPromocion(
            TiposPromocion tipoPromocion) {

        return repository.save(tipoPromocion);
    }

    public TiposPromocion obtenerTipoPromocion(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Tipo de promoción no encontrado"
                ));
    }

    public List<TiposPromocion> listarTiposPromocion() {
        return repository.findAll();
    }

    public TiposPromocion actualizarTipoPromocion(
            Long id,
            TiposPromocion datos) {

        TiposPromocion tipoPromocion =
                obtenerTipoPromocion(id);

        tipoPromocion.setNombre(datos.getNombre());
        tipoPromocion.setPrioridad(
                datos.getPrioridad()
        );
        tipoPromocion.setDuracion_dias(
                datos.getDuracion_dias()
        );
        tipoPromocion.setPrecio(datos.getPrecio());

        return repository.save(tipoPromocion);
    }

    public void eliminarTipoPromocion(Long id) {
        TiposPromocion tipoPromocion =
                obtenerTipoPromocion(id);

        repository.delete(tipoPromocion);
    }
}