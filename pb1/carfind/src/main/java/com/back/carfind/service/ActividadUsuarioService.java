package com.back.carfind.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.back.carfind.model.ActividadUsuario;
import com.back.carfind.repository.ActividadUsuarioRepository;
@Service
public class ActividadUsuarioService {

@Autowired
private ActividadUsuarioRepository repository;
public ActividadUsuario crearActividadUsuario(ActividadUsuario actividadUsuario) {
return repository.save(actividadUsuario);
}
public ActividadUsuario obtenerActividadUsuario(Long id) {
return repository.findById(id)
.orElseThrow(() -> new RuntimeException("actividad no encontrada"));

}
public List<ActividadUsuario> listarActividadUsuario() {
return repository.findAll();
}
public ActividadUsuario actualizarActividadUsuario(Long id, ActividadUsuario datos) {
ActividadUsuario actividadUsuario = obtenerActividadUsuario(id);
actividadUsuario.setId_actividadUsuario(datos.getId_actividadUsuario());
actividadUsuario.setAccion(datos.getAccion());
actividadUsuario.setDescripcion(datos.getDescripcion());
actividadUsuario.setFecha_accion(datos.getFecha_accion());
return repository.save(actividadUsuario);
}
public void eliminaractividadUsuario (Long id) {
repository.deleteById(id);
}
}