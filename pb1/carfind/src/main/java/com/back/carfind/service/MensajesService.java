package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.Mensajes;
import com.back.carfind.repository.MensajesRepository;

@Service
public class MensajesService {

    @Autowired
    private MensajesRepository repository;

    public Mensajes crearMensaje(Mensajes mensaje) {
        return repository.save(mensaje);
    }

    public Mensajes obtenerMensaje(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Mensaje no encontrado"
                ));
    }

    public List<Mensajes> listarMensajes() {
        return repository.findAll();
    }

    public Mensajes actualizarMensaje(Long id, Mensajes datos) {
        Mensajes mensaje = obtenerMensaje(id);

        mensaje.setId_chat(datos.getId_chat());
        mensaje.setId_emisor(datos.getId_emisor());
        mensaje.setContenido(datos.getContenido());
        mensaje.setFecha(datos.getFecha());
        mensaje.setLeido(datos.getLeido());

        return repository.save(mensaje);
    }

    public void eliminarMensaje(Long id) {
        Mensajes mensaje = obtenerMensaje(id);

        repository.delete(mensaje);
    }
}