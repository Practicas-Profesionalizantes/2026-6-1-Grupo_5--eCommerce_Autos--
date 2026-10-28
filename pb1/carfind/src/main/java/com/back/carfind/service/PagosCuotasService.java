package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.PagosCuotas;
import com.back.carfind.repository.PagosCuotasRepository;

@Service
public class PagosCuotasService {

    @Autowired
    private PagosCuotasRepository repository;

    public PagosCuotas crearPagoCuota(PagosCuotas pagoCuota) {
        return repository.save(pagoCuota);
    }

    public PagosCuotas obtenerPagoCuota(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago en cuotas no encontrado"));
    }

    public List<PagosCuotas> listarPagosCuotas() {
        return repository.findAll();
    }

    public PagosCuotas actualizarPagoCuota(Long id, PagosCuotas datos) {
        PagosCuotas pagoCuota = obtenerPagoCuota(id);
        pagoCuota.setTransaccionId(datos.getTransaccionId());
        pagoCuota.setPlanId(datos.getPlanId());
        pagoCuota.setCantidadCuotas(datos.getCantidadCuotas());
        pagoCuota.setInteresAplicado(datos.getInteresAplicado());
        pagoCuota.setMontoOriginal(datos.getMontoOriginal());
        pagoCuota.setMontoTotal(datos.getMontoTotal());
        pagoCuota.setMontoCuota(datos.getMontoCuota());
        pagoCuota.setMoneda(datos.getMoneda());
        pagoCuota.setFechaInicio(datos.getFechaInicio());
        return repository.save(pagoCuota);
    }

    public void eliminarPagoCuota(Long id) {
        PagosCuotas pagoCuota = obtenerPagoCuota(id);
        repository.delete(pagoCuota);
    }
}
