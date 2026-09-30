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
                .orElseThrow(() -> new RuntimeException(
                        "Promoción no encontrada"
                ));
    }

    public List<Promociones> listarPromociones() {
        return repository.findAll();
    }

    public Promociones actualizarPromocion(
            Long id,
            Promociones datos) {

        Promociones promocion = obtenerPromocion(id);

        promocion.setId_publicacion(
                datos.getId_publicacion()
        );

        promocion.setId_tipo_promocion(
                datos.getId_tipo_promocion()
        );

        promocion.setFecha_inicio(
                datos.getFecha_inicio()
        );

        promocion.setFehca_fin(
                datos.getFehca_fin()
        );

        promocion.setEstado(
                datos.getEstado()
        );

        return repository.save(promocion);
    }

    public void eliminarPromocion(Long id) {
        Promociones promocion = obtenerPromocion(id);

        repository.delete(promocion);
    }
}