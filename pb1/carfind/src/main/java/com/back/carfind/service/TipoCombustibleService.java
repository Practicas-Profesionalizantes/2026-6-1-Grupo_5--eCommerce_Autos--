package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.TipoCombustible;
import com.back.carfind.repository.TipoCombustibleRepository;

@Service
public class TipoCombustibleService {

    @Autowired
    private TipoCombustibleRepository repository;

    public TipoCombustible crearTipoCombustible(TipoCombustible tipoCombustible) {
        return repository.save(tipoCombustible);
    }

    public TipoCombustible obtenerTipoCombustible(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de combustible no encontrado"));
    }

    public List<TipoCombustible> listarTiposCombustible() {
        return repository.findAll();
    }

    public TipoCombustible actualizarTipoCombustible(Long id, TipoCombustible datos) {
        TipoCombustible tipoCombustible = obtenerTipoCombustible(id);
        tipoCombustible.setNombre(datos.getNombre());
        return repository.save(tipoCombustible);
    }

    public void eliminarTipoCombustible(Long id) {
        TipoCombustible tipoCombustible = obtenerTipoCombustible(id);
        repository.delete(tipoCombustible);
    }
}
