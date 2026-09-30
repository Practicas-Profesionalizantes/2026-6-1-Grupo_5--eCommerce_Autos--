package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.Movimientos;
import com.back.carfind.repository.MovimientosRepository;

@Service
public class MovimientosService {

    @Autowired
    private MovimientosRepository repository;

    public Movimientos crearMovimiento(Movimientos movimiento) {
        return repository.save(movimiento);
    }

    public Movimientos obtenerMovimiento(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Movimiento no encontrado"
                ));
    }

    public List<Movimientos> listarMovimientos() {
        return repository.findAll();
    }

    public Movimientos actualizarMovimiento(
            Long id,
            Movimientos datos) {

        Movimientos movimiento = obtenerMovimiento(id);

        movimiento.setId_usuario(datos.getId_usuario());
        movimiento.setTipo(datos.getTipo());
        movimiento.setMonto(datos.getMonto());
        movimiento.setDescripcion(datos.getDescripcion());
        movimiento.setFecha(datos.getFecha());

        return repository.save(movimiento);
    }

    public void eliminarMovimiento(Long id) {
        Movimientos movimiento = obtenerMovimiento(id);

        repository.delete(movimiento);
    }
}