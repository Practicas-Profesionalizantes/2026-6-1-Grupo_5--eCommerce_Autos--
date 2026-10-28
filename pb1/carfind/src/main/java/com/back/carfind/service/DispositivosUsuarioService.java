package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.DispositivosUsuario;
import com.back.carfind.repository.DispositivosUsuarioRepository;

@Service
public class DispositivosUsuarioService {

    @Autowired
    private DispositivosUsuarioRepository repository;

    public DispositivosUsuario crearDispositivoUsuario(DispositivosUsuario dispositivoUsuario) {
        return repository.save(dispositivoUsuario);
    }

    public DispositivosUsuario obtenerDispositivoUsuario(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dispositivo de usuario no encontrado"));
    }

    public List<DispositivosUsuario> listarDispositivosUsuario() {
        return repository.findAll();
    }

    public DispositivosUsuario actualizarDispositivoUsuario(Long id, DispositivosUsuario datos) {
        DispositivosUsuario dispositivoUsuario = obtenerDispositivoUsuario(id);
        dispositivoUsuario.setUsuarioId(datos.getUsuarioId());
        dispositivoUsuario.setTokenPush(datos.getTokenPush());
        dispositivoUsuario.setPlataforma(datos.getPlataforma());
        return repository.save(dispositivoUsuario);
    }

    public void eliminarDispositivoUsuario(Long id) {
        DispositivosUsuario dispositivoUsuario = obtenerDispositivoUsuario(id);
        repository.delete(dispositivoUsuario);
    }
}
