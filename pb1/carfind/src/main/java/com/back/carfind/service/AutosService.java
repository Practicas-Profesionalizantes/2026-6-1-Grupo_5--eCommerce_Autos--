package com.back.carfind.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.back.carfind.model.Autos;
import com.back.carfind.repository.AutosRepository;
@Service
public class AutosService {

@Autowired
private AutosRepository repository;
public Autos crearAutos(Autos autos) {
return repository.save(autos);
}
public Autos obtenerAutos(Long id) {
return repository.findById(id)
.orElseThrow(() -> new RuntimeException("Auto no encontrado"));

}
public List<Autos> listarAutos() {
return repository.findAll();
}
public Autos actualizarAutos(Long id, Autos datos) {
Autos autos = obtenerAutos(id);
autos.setId_auto(datos.getId_auto());
autos.setModelo_id(datos.getModelo_id());
autos.setVersion(datos.getVersion());
autos.setAnio(datos.getAnio());
autos.setKms(datos.getKms());
autos.setVtv(datos.getVtv());
autos.setGrabado(datos.getGrabado());
autos.setCombustible_id(datos.getCombustible_id());
autos.setTransmision_id(datos.getTransmision_id());
autos.setColor(datos.getColor());
autos.setPuertas(datos.getPuertas());
autos.setDescripcion(datos.getDescripcion());
autos.setFecha_creacion(datos.getFecha_creacion());
return repository.save(autos);
}
public void eliminarautos (Long id) {
repository.deleteById(id);
}
}