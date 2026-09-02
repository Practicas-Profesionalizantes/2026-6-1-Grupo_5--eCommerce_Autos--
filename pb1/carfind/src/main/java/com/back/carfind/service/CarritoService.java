package com.back.carfind.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.back.carfind.model.Carrito;
import com.back.carfind.repository.CarritoRepository;
@Service
public class CarritoService {

@Autowired
private CarritoRepository repository;
public Carrito crearCarrito(Carrito carrito) {
return repository.save(carrito);
}
public Carrito obtenerCarrito(Long id) {
return repository.findById(id)
.orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

}
public List<Carrito> listarCarrito() {
return repository.findAll();
}
public Carrito actualizarCarrito(Long id, Carrito datos) {
Carrito carrito = obtenerCarrito(id);
carrito.setId_usuario(datos.getId_usuario());
carrito.setId_publicacion(datos.getId_publicacion());
carrito.setCantidad(datos.getCantidad());
carrito.setPrecio_total(datos.getPrecio_total());

return repository.save(carrito);
}
public void eliminarcarrito (Long id) {
repository.deleteById(id);
}
}