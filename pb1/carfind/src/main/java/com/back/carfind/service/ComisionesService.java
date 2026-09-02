package com.back.carfind.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.back.carfind.model.Comisiones;
import com.back.carfind.repository.ComisionesRepository;
@Service
public class ComisionesService {

@Autowired
private ComisionesRepository repository;
public Comisiones crearComisiones(Comisiones comisiones) {
return repository.save(comisiones);
}
public Comisiones obtenerComisiones(Long id) {
return repository.findById(id)
.orElseThrow(() -> new RuntimeException("Comision no encontrada"));

}
public List<Comisiones> listarComisiones() {
return repository.findAll();
}
public Comisiones actualizarComisiones(Long id, Comisiones datos) {
Comisiones comisiones = obtenerComisiones(id);
comisiones.setId_compra(datos.getId_compra());
comisiones.setPorcentaje(datos.getPorcentaje());
comisiones.setMonto(datos.getMonto());

return repository.save(comisiones);
}
public void eliminarcomisiones (Long id) {
repository.deleteById(id);
}
}