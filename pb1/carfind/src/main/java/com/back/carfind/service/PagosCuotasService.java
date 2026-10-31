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
                .orElseThrow(() -> new RuntimeException(
                        "Pago de cuota no encontrado"
                ));
    }

    public List<PagosCuotas> listarPagosCuotas() {
        return repository.findAll();
    }

    public PagosCuotas actualizarPagoCuota(
            Long id,
            PagosCuotas datos) {

        PagosCuotas pagoCuota = obtenerPagoCuota(id);

        pagoCuota.setId_pago(datos.getId_pago());
        pagoCuota.setId_plan(datos.getId_plan());
        pagoCuota.setCantidad_cuotas(
                datos.getCantidad_cuotas()
        );
        pagoCuota.setInteres_aplicado(
                datos.getInteres_aplicado()
        );
        pagoCuota.setMonto_original(
                datos.getMonto_original()
        );
        pagoCuota.setMonto_total(
                datos.getMonto_total()
        );
        pagoCuota.setMonto_cuota(
                datos.getMonto_cuota()
        );
        pagoCuota.setMoneda(datos.getMoneda());
        pagoCuota.setFecha_inicio(
                datos.getFecha_inicio()
        );

        return repository.save(pagoCuota);
    }

    public void eliminarPagoCuota(Long id) {
        PagosCuotas pagoCuota = obtenerPagoCuota(id);

        repository.delete(pagoCuota);
    }
}