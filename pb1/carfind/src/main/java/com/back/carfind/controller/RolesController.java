package com.back.carfind.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import com.back.carfind.model.Roles;
import com.back.carfind.service.RolesService;
@RestController
@RequestMapping("/roles")
public class RolesController {
@Autowired
private RolesService service;
@GetMapping
public List<Roles> listar() {
return service.listarRoles();
}
@GetMapping("/{id}")
public Roles obtener(@PathVariable Long id_rol) {
return service.obtenerRol(id_rol);
}
@PostMapping
public Roles crear(@RequestBody Roles rol) {
return service.crearRol(rol);
}
@PutMapping("/{id}")
public Roles actualizar(@PathVariable Long id_rol, @RequestBody Roles rol) {
return service.actualizarRoles(id_rol, rol);
}
@DeleteMapping("/{id}")
public void eliminar(@PathVariable Long id_rol) {
service.eliminarRoles(id_rol);
}
}