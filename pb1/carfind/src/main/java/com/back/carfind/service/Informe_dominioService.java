package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.Informe_dominio;
import com.back.carfind.repository.Informe_dominioRepository;

@Service
public class Informe_dominioService {

    @Autowired
    private Informe_dominioRepository repository;

    public Informe_dominio crearInforme(Informe_dominio informe) {
        return repository.save(informe);
    }

    public Informe_dominio obtenerInforme(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Informe de dominio no encontrado"
                ));
    }

    public List<Informe_dominio> listarInformes() {
        return repository.findAll();
    }

    public Informe_dominio actualizarInforme(
            Long id,
            Informe_dominio datos) {

        Informe_dominio informe = obtenerInforme(id);

        informe.setAuto(datos.getAuto());
        informe.setDominio(datos.getDominio());
        informe.setNumero_chasis(datos.getNumero_chasis());
        informe.setNumero_motor(datos.getNumero_motor());
        informe.setTitular_actual(datos.getTitular_actual());
        informe.setCantidad_titulares(
                datos.getCantidad_titulares()
        );
        informe.setFecha_inscripcion(
                datos.getFecha_inscripcion()
        );
        informe.setTiene_embargo(
                datos.getTiene_embargo()
        );
        informe.setTiene_prenda(
                datos.getTiene_prenda()
        );
        informe.setEs_robado(
                datos.getEs_robado()
        );
        informe.setTitular_inhibido(
                datos.getTitular_inhibido()
        );
        informe.setDeuda_patentes(
                datos.getDeuda_patentes()
        );
        informe.setDeuda_multas(
                datos.getDeuda_multas()
        );
        informe.setObservaciones(
                datos.getObservaciones()
        );
        informe.setFecha_emision(
                datos.getFecha_emision()
        );
        informe.setFecha_vencimiento(
                datos.getFecha_vencimiento()
        );

        return repository.save(informe);
    }

    public void eliminarInforme(Long id) {
        Informe_dominio informe = obtenerInforme(id);
        repository.delete(informe);
    }
}