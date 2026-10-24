package com.back.carfind.controller;

import com.back.carfind.model.Chat;
import com.back.carfind.service.ChatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chats")
@CrossOrigin(origins = "*")
public class ChatController {

    private final ChatService service;

    public ChatController(ChatService service) {
        this.service = service;
    }

    @PostMapping
    public Chat crearChat(@RequestBody Chat chat) {
        return service.crearChat(chat);
    }

    @GetMapping("/{id}")
    public Chat obtenerChat(@PathVariable Long id) {
        return service.obtenerChat(id);
    }

    @GetMapping
    public List<Chat> listarChats() {
        return service.listarChat();
    }

    @PutMapping("/{id}")
    public Chat actualizarChat(@PathVariable Long id, @RequestBody Chat chat) {
        return service.actualizarChat(id, chat);
    }

    @DeleteMapping("/{id}")
    public void eliminarChat(@PathVariable Long id) {
        service.eliminarchat(id);
    }
}
