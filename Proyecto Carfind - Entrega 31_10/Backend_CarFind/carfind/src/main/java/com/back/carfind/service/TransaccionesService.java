package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.Transacciones;
import com.back.carfind.repository.TransaccionesRepository;

@Service
public class TransaccionesService {

    @Autowired
    private TransaccionesRepository repository;

    public Transacciones crearTransaccion(Transacciones transaccion) {
        return repository.save(transaccion);
    }

    public Transacciones obtenerTransaccion(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaccion no encontrada"));
    }

    public List<Transacciones> listarTransacciones() {
        return repository.findAll();
    }

    public Transacciones actualizarTransaccion(Long id, Transacciones datos) {
        Transacciones transaccion = obtenerTransaccion(id);
        transaccion.setCompraId(datos.getCompraId());
        transaccion.setTipoPagoId(datos.getTipoPagoId());
        transaccion.setEstado(datos.getEstado());
        transaccion.setIdExterno(datos.getIdExterno());
        transaccion.setFecha(datos.getFecha());
        return repository.save(transaccion);
    }

    public void eliminarTransaccion(Long id) {
        Transacciones transaccion = obtenerTransaccion(id);
        repository.delete(transaccion);
    }
}
