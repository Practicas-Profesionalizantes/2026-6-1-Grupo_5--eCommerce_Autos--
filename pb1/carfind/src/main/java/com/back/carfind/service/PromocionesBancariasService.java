package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.PromocionesBancarias;
import com.back.carfind.repository.PromocionesBancariasRepository;

@Service
public class PromocionesBancariasService {

    @Autowired
    private PromocionesBancariasRepository repository;

    public PromocionesBancarias crearPromocion(
            PromocionesBancarias promocion) {

        return repository.save(promocion);
    }

    public PromocionesBancarias obtenerPromocion(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Promoción bancaria no encontrada"
                ));
    }

    public List<PromocionesBancarias> listarPromociones() {
        return repository.findAll();
    }

    public PromocionesBancarias actualizarPromocion(
            Long id,
            PromocionesBancarias datos) {

        PromocionesBancarias promocion =
                obtenerPromocion(id);

        promocion.setId_banco(datos.getId_banco());
        promocion.setTarjeta(datos.getTarjeta());
        promocion.setCuotas(datos.getCuotas());
        promocion.setInteres(datos.getInteres());
        promocion.setActiva(datos.getActiva());

        return repository.save(promocion);
    }

    public void eliminarPromocion(Long id) {
        PromocionesBancarias promocion =
                obtenerPromocion(id);

        repository.delete(promocion);
    }
}