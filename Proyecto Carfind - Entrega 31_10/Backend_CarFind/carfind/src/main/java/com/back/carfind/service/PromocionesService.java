package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.Promociones;
import com.back.carfind.repository.PromocionesRepository;

@Service
public class PromocionesService {

    @Autowired
    private PromocionesRepository repository;

    public Promociones crearPromocion(Promociones promocion) {
        return repository.save(promocion);
    }

    public Promociones obtenerPromocion(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Promocion no encontrada"));
    }

    public List<Promociones> listarPromociones() {
        return repository.findAll();
    }

    public Promociones actualizarPromocion(Long id, Promociones datos) {
        Promociones promocion = obtenerPromocion(id);
        promocion.setPublicacionId(datos.getPublicacionId());
        promocion.setTipoPromocionId(datos.getTipoPromocionId());
        promocion.setFechaInicio(datos.getFechaInicio());
        promocion.setFechaFin(datos.getFechaFin());
        promocion.setEstado(datos.getEstado());
        return repository.save(promocion);
    }

    public void eliminarPromocion(Long id) {
        Promociones promocion = obtenerPromocion(id);
        repository.delete(promocion);
    }
}
