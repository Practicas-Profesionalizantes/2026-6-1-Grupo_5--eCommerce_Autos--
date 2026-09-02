package com.back.carfind.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.back.carfind.model.BusquedasGuardadas;
import com.back.carfind.repository.BusquedasGuardadasRepository;
@Service
public class BusquedasGuardadasService {

@Autowired
private BusquedasGuardadasRepository repository;
public BusquedasGuardadas crearBusquedasGuardadas(BusquedasGuardadas busquedasGuardadas) {
return repository.save(busquedasGuardadas);
}
public BusquedasGuardadas obtenerBusquedasGuardadas(Long id) {
return repository.findById(id)
.orElseThrow(() -> new RuntimeException("busqueda no encontrada"));

}
public List<BusquedasGuardadas> listarBusquedasGuardadas() {
return repository.findAll();
}
public BusquedasGuardadas actualizarBusquedasGuardadas(Long id, BusquedasGuardadas datos) {
BusquedasGuardadas busquedasGuardadas = obtenerBusquedasGuardadas(id);
busquedasGuardadas.setUsuario_id(datos.getUsuario_id());
busquedasGuardadas.setQuery(datos.getQuery());
busquedasGuardadas.setFecha(datos.getFecha());

return repository.save(busquedasGuardadas);
}
public void eliminarbusquedasGuardadas (Long id) {
repository.deleteById(id);
}
}