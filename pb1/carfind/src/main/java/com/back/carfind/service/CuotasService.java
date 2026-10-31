package com.back.carfind.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.back.carfind.model.Cuotas;
import com.back.carfind.repository.CuotasRepository;
@Service
public class CuotasService {

@Autowired
private CuotasRepository repository;
public Cuotas crearCuotas(Cuotas cuotas) {
return repository.save(cuotas);
}
public Cuotas obtenerCuotas(Long id) {
return repository.findById(id)
.orElseThrow(() -> new RuntimeException("Cuotas no encontradas"));

}
public List<Cuotas> listarCuotas() {
return repository.findAll();
}
public Cuotas actualizarCuotas(Long id, Cuotas datos) {
Cuotas cuotas = obtenerCuotas(id);
cuotas.setId_cuotas(datos.getId_cuotas());
cuotas.setId_pago_cuotas(datos.getId_pago_cuotas());
cuotas.setNumero_cuota(datos.getNumero_cuota());
cuotas.setMonto(datos.getMonto());
cuotas.setEstado(datos.getEstado());
cuotas.setFecha_vencimiento(datos.getFecha_vencimiento());
cuotas.setFecha_pago(datos.getFecha_pago());

return repository.save(cuotas);
}
public void eliminarcuotas (Long id) {
repository.deleteById(id);
}
}