package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.Cuotas;
import com.back.carfind.repository.CuotasRepository;

@Service
public class CuotasService {

    @Autowired
    private CuotasRepository repository;

    public Cuotas crearCuota(Cuotas cuota) {
        return repository.save(cuota);
    }

    public Cuotas obtenerCuota(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuota no encontrada"));
    }

    public List<Cuotas> listarCuotas() {
        return repository.findAll();
    }

    public Cuotas actualizarCuota(Long id, Cuotas datos) {
        Cuotas cuota = obtenerCuota(id);
        cuota.setPagoCuotaId(datos.getPagoCuotaId());
        cuota.setNumeroCuota(datos.getNumeroCuota());
        cuota.setMonto(datos.getMonto());
        cuota.setEstado(datos.getEstado());
        cuota.setFechaVencimiento(datos.getFechaVencimiento());
        cuota.setFechaPago(datos.getFechaPago());
        return repository.save(cuota);
    }

    public void eliminarCuota(Long id) {
        Cuotas cuota = obtenerCuota(id);
        repository.delete(cuota);
    }
}
