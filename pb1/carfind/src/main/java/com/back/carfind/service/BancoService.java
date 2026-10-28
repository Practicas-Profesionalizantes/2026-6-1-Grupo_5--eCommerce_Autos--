package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.Banco;
import com.back.carfind.repository.BancoRepository;

@Service
public class BancoService {

    @Autowired
    private BancoRepository repository;

    public Banco crearBanco(Banco banco) {
        return repository.save(banco);
    }

    public Banco obtenerBanco(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Banco no encontrado"));
    }

    public List<Banco> listarBancos() {
        return repository.findAll();
    }

    public Banco actualizarBanco(Long id, Banco datos) {
        Banco banco = obtenerBanco(id);
        banco.setNombre(datos.getNombre());
        banco.setCodigo(datos.getCodigo());
        banco.setActivo(datos.getActivo());
        banco.setFechaCreacion(datos.getFechaCreacion());
        return repository.save(banco);
    }

    public void eliminarBanco(Long id) {
        Banco banco = obtenerBanco(id);
        repository.delete(banco);
    }
}
