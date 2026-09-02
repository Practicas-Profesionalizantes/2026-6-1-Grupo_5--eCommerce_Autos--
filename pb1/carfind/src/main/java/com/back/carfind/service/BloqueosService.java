package com.back.carfind.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.back.carfind.model.Bloqueos;
import com.back.carfind.repository.BloqueosRepository;
@Service
public class BloqueosService {

@Autowired
private BloqueosRepository repository;
public Bloqueos crearBloqueos(Bloqueos bloqueos) {
return repository.save(bloqueos);
}
public Bloqueos obtenerBloqueos(Long id) {
return repository.findById(id)
.orElseThrow(() -> new RuntimeException("Bloqueo no encontrado"));

}
public List<Bloqueos> listarBloqueos() {
return repository.findAll();
}
public Bloqueos actualizarBloqueos(Long id, Bloqueos datos) {
Bloqueos bloqueos = obtenerBloqueos(id);
bloqueos.setId_usuario(datos.getId_usuario());
bloqueos.setUsuario_bloqueado_id(datos.getUsuario_bloqueado_id());

return repository.save(bloqueos);
}
public void eliminarbloqueos (Long id) {
repository.deleteById(id);
}
}