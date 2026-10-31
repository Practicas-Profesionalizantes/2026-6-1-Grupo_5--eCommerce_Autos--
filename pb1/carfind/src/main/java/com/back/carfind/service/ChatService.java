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
public List<Chat> listarChat() {
return repository.findAll();
}
public Chat actualizarChat(Long id, Chat datos) {
Chat chat = obtenerChat(id);
chat.setId_chat(datos.getId_chat());
chat.setId_usuario1(datos.getId_usuario1());
chat.setId_usuario2(datos.getId_usuario2());
chat.setFecha_creacion(datos.getFecha_creacion());

return repository.save(chat);
}
public void eliminarchat (Long id) {
repository.deleteById(id);
}
}