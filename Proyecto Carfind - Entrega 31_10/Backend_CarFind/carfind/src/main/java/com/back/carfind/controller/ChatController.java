package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.ChatRequestDTO;
import com.back.carfind.dtos.ChatResponseDTO;
import com.back.carfind.model.Chat;
import com.back.carfind.service.ChatService;

@RestController
@RequestMapping("/api/chats")
@CrossOrigin(origins = "*")
public class ChatController {

    private final ChatService service;
    private final ModelMapper mapper;

    public ChatController(ChatService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ChatResponseDTO crearChat(@RequestBody ChatRequestDTO dto) {
        return aDto(service.crearChat(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public ChatResponseDTO obtenerChat(@PathVariable Long id) {
        return aDto(service.obtenerChat(id));
    }

    @GetMapping
    public List<ChatResponseDTO> listarChats() {
        return service.listarChats().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public ChatResponseDTO actualizarChat(@PathVariable Long id, @RequestBody ChatRequestDTO dto) {
        return aDto(service.actualizarChat(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarChat(@PathVariable Long id) {
        service.eliminarChat(id);
    }

    private Chat aEntidad(ChatRequestDTO dto) {
        return mapper.map(dto, Chat.class);
    }

    private ChatResponseDTO aDto(Chat entidad) {
        return mapper.map(entidad, ChatResponseDTO.class);
    }
}
