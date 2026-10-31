package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.TipoPago;
import com.back.carfind.repository.TipoPagoRepository;

@Service
public class TipoPagoService {

    @Autowired
    private TipoPagoRepository repository;

    public TipoPago crearTipoPago(TipoPago tipoPago) {
        return repository.save(tipoPago);
    }

    public TipoPago obtenerTipoPago(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Tipo de pago no encontrado"
                ));
    }

    public List<TipoPago> listarTiposPago() {
        return repository.findAll();
    }

    public TipoPago actualizarTipoPago(
            Long id,
            TipoPago datos) {

        TipoPago tipoPago = obtenerTipoPago(id);

        tipoPago.setNombre_tipo_pago(
                datos.getNombre_tipo_pago()
        );

        return repository.save(tipoPago);
    }

    public void eliminarTipoPago(Long id) {
        TipoPago tipoPago = obtenerTipoPago(id);

        repository.delete(tipoPago);
    }
}