package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.MetodosPago;
import com.back.carfind.repository.MetodosPagoRepository;

@Service
public class MetodosPagoService {

    @Autowired
    private MetodosPagoRepository repository;

    public MetodosPago crearMetodoPago(MetodosPago metodoPago) {
        return repository.save(metodoPago);
    }

    public MetodosPago obtenerMetodoPago(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Método de pago no encontrado"
                ));
    }

    public List<MetodosPago> listarMetodosPago() {
        return repository.findAll();
    }

    public MetodosPago actualizarMetodoPago(
            Long id,
            MetodosPago datos) {

        MetodosPago metodoPago = obtenerMetodoPago(id);

        metodoPago.setId_usuario(datos.getId_usuario());
        metodoPago.setDatos_tokenizados(
                datos.getDatos_tokenizados()
        );
        metodoPago.setActivo_metodo(
                datos.getActivo_metodo()
        );
        metodoPago.setId_tipo_pago(
                datos.getId_tipo_pago()
        );

        return repository.save(metodoPago);
    }

    public void eliminarMetodoPago(Long id) {
        MetodosPago metodoPago = obtenerMetodoPago(id);

        repository.delete(metodoPago);
    }
}