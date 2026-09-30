package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.Tipo_combustible;
import com.back.carfind.repository.TipoCombustibleRepository;

@Service
public class Tipo_combustibleService {

    @Autowired
    private TipoCombustibleRepository repository;

    public Tipo_combustible crearTipoCombustible(
            Tipo_combustible tipoCombustible) {

        return repository.save(tipoCombustible);
    }

    public Tipo_combustible obtenerTipoCombustible(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Tipo de combustible no encontrado"
                ));
    }

    public List<Tipo_combustible> listarTiposCombustible() {
        return repository.findAll();
    }

    public Tipo_combustible actualizarTipoCombustible(
            Long id,
            Tipo_combustible datos) {

        Tipo_combustible tipoCombustible =
                obtenerTipoCombustible(id);

        tipoCombustible.setNombre_combustible(
                datos.getNombre_combustible()
        );

        return repository.save(tipoCombustible);
    }

    public void eliminarTipoCombustible(Long id) {
        Tipo_combustible tipoCombustible =
                obtenerTipoCombustible(id);

        repository.delete(tipoCombustible);
    }
}