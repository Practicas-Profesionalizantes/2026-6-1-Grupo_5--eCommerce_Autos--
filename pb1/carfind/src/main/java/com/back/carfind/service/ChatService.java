package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.Chat;
import com.back.carfind.repository.ChatRepository;

@Service
public class ChatService {

    @Autowired
    private ChatRepository repository;

    public Chat crearChat(Chat chat) {
        return repository.save(chat);
    }

    public Chat obtenerChat(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chat no encontrado"));
    }

    public List<Chat> listarChats() {
        return repository.findAll();
    }

    public Chat actualizarChat(Long id, Chat datos) {
        Chat chat = obtenerChat(id);
        chat.setUsuario1Id(datos.getUsuario1Id());
        chat.setUsuario2Id(datos.getUsuario2Id());
        chat.setPublicacionId(datos.getPublicacionId());
        chat.setFechaCreacion(datos.getFechaCreacion());
        return repository.save(chat);
    }

    public void eliminarChat(Long id) {
        Chat chat = obtenerChat(id);
        repository.delete(chat);
    }
}
