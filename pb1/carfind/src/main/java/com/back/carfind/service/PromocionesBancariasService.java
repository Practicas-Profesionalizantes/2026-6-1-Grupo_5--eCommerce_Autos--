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

    public PromocionesBancarias crearPromocionBancaria(PromocionesBancarias promocionBancaria) {
        return repository.save(promocionBancaria);
    }

    public PromocionesBancarias obtenerPromocionBancaria(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Promocion bancaria no encontrada"));
    }

    public List<PromocionesBancarias> listarPromocionesBancarias() {
        return repository.findAll();
    }

    public PromocionesBancarias actualizarPromocionBancaria(Long id, PromocionesBancarias datos) {
        PromocionesBancarias promocionBancaria = obtenerPromocionBancaria(id);
        promocionBancaria.setBancoId(datos.getBancoId());
        promocionBancaria.setTarjeta(datos.getTarjeta());
        promocionBancaria.setCuotas(datos.getCuotas());
        promocionBancaria.setInteres(datos.getInteres());
        promocionBancaria.setActiva(datos.getActiva());
        return repository.save(promocionBancaria);
    }

    public void eliminarPromocionBancaria(Long id) {
        PromocionesBancarias promocionBancaria = obtenerPromocionBancaria(id);
        repository.delete(promocionBancaria);
    }
}
