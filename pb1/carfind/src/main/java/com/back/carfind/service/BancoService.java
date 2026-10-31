package com.back.carfind.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.back.carfind.model.Banco;
import com.back.carfind.repository.BancoRepository;
@Service
public class BancoService {

@Autowired
private BancoRepository repository;
public Banco crearBanco(Banco banco) {
return repository.save(banco);
}
public Banco obtenerBanco(Long id) {
return repository.findById(id)
.orElseThrow(() -> new RuntimeException("Banco no encontrado"));

}
public List<Banco> listarBanco() {
return repository.findAll();
}
public Banco actualizarBanco(Long id, Banco datos) {
Banco banco = obtenerBanco(id);
banco.setId_banco(datos.getId_banco());
banco.setNombre(datos.getNombre());
banco.setCodigo(datos.getCodigo());
banco.setActivo(datos.getActivo());
banco.setFecha_creacion(datos.getFecha_creacion());
return repository.save(banco);
}
public void eliminarbanco (Long id) {
repository.deleteById(id);
}
}