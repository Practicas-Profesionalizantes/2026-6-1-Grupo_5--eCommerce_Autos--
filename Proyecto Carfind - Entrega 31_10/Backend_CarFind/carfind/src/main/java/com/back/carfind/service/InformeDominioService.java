package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.InformeDominio;
import com.back.carfind.repository.InformeDominioRepository;

@Service
public class InformeDominioService {

    @Autowired
    private InformeDominioRepository repository;

    public InformeDominio crearInformeDominio(InformeDominio informeDominio) {
        return repository.save(informeDominio);
    }

    public InformeDominio obtenerInformeDominio(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Informe de dominio no encontrado"));
    }

    public List<InformeDominio> listarInformesDominio() {
        return repository.findAll();
    }

    public InformeDominio actualizarInformeDominio(Long id, InformeDominio datos) {
        InformeDominio informeDominio = obtenerInformeDominio(id);
        informeDominio.setAutoId(datos.getAutoId());
        informeDominio.setDominio(datos.getDominio());
        informeDominio.setNumeroChasis(datos.getNumeroChasis());
        informeDominio.setNumeroMotor(datos.getNumeroMotor());
        informeDominio.setTitularActual(datos.getTitularActual());
        informeDominio.setCantidadTitulares(datos.getCantidadTitulares());
        informeDominio.setFechaInscripcion(datos.getFechaInscripcion());
        informeDominio.setTieneEmbargo(datos.getTieneEmbargo());
        informeDominio.setTienePrenda(datos.getTienePrenda());
        informeDominio.setEsRobado(datos.getEsRobado());
        informeDominio.setTitularInhibido(datos.getTitularInhibido());
        informeDominio.setDeudaPatentes(datos.getDeudaPatentes());
        informeDominio.setDeudaMultas(datos.getDeudaMultas());
        informeDominio.setObservaciones(datos.getObservaciones());
        informeDominio.setFechaEmision(datos.getFechaEmision());
        informeDominio.setFechaVencimiento(datos.getFechaVencimiento());
        return repository.save(informeDominio);
    }

    public void eliminarInformeDominio(Long id) {
        InformeDominio informeDominio = obtenerInformeDominio(id);
        repository.delete(informeDominio);
    }
}
