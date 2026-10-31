package com.back.carfind.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.back.carfind.model.Direcciones;
import com.back.carfind.repository.DireccionesRepository;
@Service
public class DireccionesService {

@Autowired
private DireccionesRepository repository;
public Direcciones crearDirecciones(Direcciones direcciones) {
return repository.save(direcciones);
}
public Direcciones obtenerDirecciones(Long id) {
return repository.findById(id)
.orElseThrow(() -> new RuntimeException("Direcciones no encontradas"));

}
public List<Direcciones> listarDirecciones() {
return repository.findAll();
}
public Direcciones actualizarDirecciones(Long id, Direcciones datos) {
Direcciones direcciones = obtenerDirecciones(id);
direcciones.setId_direccion(datos.getId_direccion());
direcciones.setPais(datos.getPais());
direcciones.setProvincia(datos.getProvincia());
direcciones.setCiudad(datos.getCiudad());
direcciones.setDireccion(datos.getDireccion());
direcciones.setTipo(datos.getTipo());
direcciones.setEs_principal(datos.getEs_principal());

return repository.save(direcciones);
}
public void eliminarDirecciones(Long id) {
repository.deleteById(id);
}
}