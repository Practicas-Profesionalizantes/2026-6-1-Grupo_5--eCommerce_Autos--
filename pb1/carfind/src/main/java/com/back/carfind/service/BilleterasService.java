package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.Billeteras;
import com.back.carfind.repository.BilleterasRepository;

@Service
public class BilleterasService {

    @Autowired
    private BilleterasRepository repository;

    public Billeteras crearBilletera(Billeteras billetera) {
        return repository.save(billetera);
    }

    public Billeteras obtenerBilletera(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Billetera no encontrada"));
    }

    public List<Billeteras> listarBilleteras() {
        return repository.findAll();
    }

    public Billeteras actualizarBilletera(Long id, Billeteras datos) {
        Billeteras billetera = obtenerBilletera(id);
        billetera.setSaldo(datos.getSaldo());
        return repository.save(billetera);
    }

    public void eliminarBilletera(Long id) {
        Billeteras billetera = obtenerBilletera(id);
        repository.delete(billetera);
    }
}
