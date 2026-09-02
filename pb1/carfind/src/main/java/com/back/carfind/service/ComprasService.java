package com.back.carfind.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.back.carfind.model.Compras;
import com.back.carfind.repository.ComprasRepository;
@Service
public class ComprasService {

@Autowired
private ComprasRepository repository;
public Compras crearCompras(Compras compras) {
return repository.save(compras);
}
public Compras obtenerCompras(Long id) {
return repository.findById(id)
.orElseThrow(() -> new RuntimeException("Compras no encontradas"));

}
public List<Compras> listarCompras() {
return repository.findAll();
}
public Compras actualizarCompras(Long id, Compras datos) {
Compras compras = obtenerCompras(id);
compras.setId_compra(datos.getId_compra());
compras.setId_comprador(datos.getId_comprador());
compras.setId_publicacion(datos.getId_publicacion());
compras.setPrecio_final(datos.getPrecio_final());
compras.setEstado(datos.getEstado());
compras.setFecha_compra(datos.getFecha_compra());

return repository.save(compras);
}
public void eliminarcompras (Long id) {
repository.deleteById(id);
}
}