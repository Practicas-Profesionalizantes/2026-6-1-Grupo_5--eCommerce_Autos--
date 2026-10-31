package com.back.carfind.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.back.carfind.model.Billeteras;
import com.back.carfind.repository.BilleterasRepository;
@Service
public class BilleterasService {

@Autowired
private BilleterasRepository repository;
public Billeteras crearBilleteras(Billeteras billeteras) {
return repository.save(billeteras);
}
public Billeteras obtenerBilleteras(Long id) {
return repository.findById(id)
.orElseThrow(() -> new RuntimeException("Billetera no encontrada"));

}
public List<Billeteras> listarBilleteras() {
return repository.findAll();
}
public Billeteras actualizarBilleteras(Long id, Billeteras datos) {
Billeteras billeteras = obtenerBilleteras(id);
billeteras.setId_billeteras(datos.getId_billeteras());
billeteras.setId_usuario(datos.getId_usuario());
billeteras.setSaldo(datos.getSaldo());

return repository.save(billeteras);
}
public void eliminarbilleteras (Long id) {
repository.deleteById(id);
}
}