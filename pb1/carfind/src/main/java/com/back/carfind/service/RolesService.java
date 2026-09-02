package com.back.carfind.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.back.carfind.model.Roles;
import com.back.carfind.repository.RolesRepository;
@Service
public class RolesService {

@Autowired
private RolesRepository repository;
public Roles crearRol(Roles rol) {
return repository.save(rol);
}
public Roles obtenerRol(Long id) {
return repository.findById(id)
.orElseThrow(() -> new RuntimeException("Producto no encontrado"));

}
public List<Roles> listarRoles() {
return repository.findAll();
}
public Roles actualizarRoles(Long id, Roles datos) {
Roles rol = obtenerRol(id);
rol.setId_rol(datos.getId_rol());
rol.setNombre_rol(datos.getNombre_rol());
rol.setActivo_rol(datos.getActivo_rol());
return repository.save(rol);
}
public void eliminarRoles(Long id) {
repository.deleteById(id);
}
}