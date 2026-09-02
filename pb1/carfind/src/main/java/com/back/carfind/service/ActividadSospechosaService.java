package com.back.carfind.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.back.carfind.model.ActividadSospechosa;
import com.back.carfind.repository.ActividadSospechosaRepository;
@Service
public class ActividadSospechosaService {

@Autowired
private ActividadSospechosaRepository repository;
public ActividadSospechosa crearActividadSospechosa(ActividadSospechosa actividadSospechosa) {
return repository.save(actividadSospechosa);
}
public ActividadSospechosa obtenerActividadSospechosa(Long id) {
return repository.findById(id)
.orElseThrow(() -> new RuntimeException("actividad no encontrada"));

}
public List<ActividadSospechosa> listarActividadSospechosa() {
return repository.findAll();
}
public ActividadSospechosa actualizarRoles(Long id, ActividadSospechosa datos) {
ActividadSospechosa actividadSospechosa = obtenerActividadSospechosa(id);
actividadSospechosa.setId_usuario(datos.getId_usuario());
actividadSospechosa.setTipo_actividad_sospechosa(datos.getTipo_actividad_sospechosa());
actividadSospechosa.setDescripcion_actividad_sospechosa(datos.getDescripcion_actividad_sospechosa());
actividadSospechosa.setFecha_actividad_sospechosa(datos.getFecha_actividad_sospechosa());
return repository.save(actividadSospechosa);
}
public void eliminaractividadSospechosa (Long id) {
repository.deleteById(id);
}
}