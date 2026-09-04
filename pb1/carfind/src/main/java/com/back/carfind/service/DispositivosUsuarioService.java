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
public DispositivosUsuario crearDispositivosUsuario(DispositivosUsuario dispositivosUsuario) {
return repository.save(dispositivosUsuario);
}
public DispositivosUsuario obtenerDispositivosUsuario(Long id) {
return repository.findById(id)
.orElseThrow(() -> new RuntimeException("DispositivoUsuario no encontradas"));

}
public List<DispositivosUsuario> listarDispositivoUsuario() {
return repository.findAll();
}
public DispositivosUsuario actualizarDispositivosUsuario(Long id, DispositivosUsuario datos) {
DispositivosUsuario dispositivosUsuario = obtenerDispositivosUsuario(id);
dispositivosUsuario.setId_usuario(datos.getId_usuario());
dispositivosUsuario.setToken_push(datos.getToken_push());
dispositivosUsuario.setPlataforma(datos.getPlataforma());

return repository.save(dispositivosUsuario);
}
public void eliminarDispositivosUsuario (Long id) {
repository.deleteById(id);
}
}