package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.Pagos;
import com.back.carfind.repository.PagosRepository;

@Service
public class PagosService {

    @Autowired
    private PagosRepository repository;

    public Pagos crearPago(Pagos pago) {
        return repository.save(pago);
    }

    public Pagos obtenerPago(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Pago no encontrado"
                ));
    }

    public List<Pagos> listarPagos() {
        return repository.findAll();
    }

    public Pagos actualizarPago(Long id, Pagos datos) {
        Pagos pago = obtenerPago(id);

        pago.setId_compra(datos.getId_compra());
        pago.setTipo_pago_id(datos.getTipo_pago_id());
        pago.setEstado(datos.getEstado());
        pago.setNumero_transaccion(
                datos.getNumero_transaccion()
        );
        pago.setId_externo(datos.getId_externo());
        pago.setFecha_pago(datos.getFecha_pago());

        return repository.save(pago);
    }

    public void eliminarPago(Long id) {
        Pagos pago = obtenerPago(id);

        repository.delete(pago);
    }
}