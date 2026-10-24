package com.back.carfind.controller;

import com.back.carfind.model.DispositivosUsuario;
import com.back.carfind.service.DispositivosUsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dispositivos-usuario")
@CrossOrigin(origins = "*")
public class DispositivosUsuarioController {

    private final DispositivosUsuarioService service;

    public DispositivosUsuarioController(DispositivosUsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public DispositivosUsuario crearDispositivoUsuario(@RequestBody DispositivosUsuario dispositivoUsuario) {
        return service.crearDispositivosUsuario(dispositivoUsuario);
    }

    @GetMapping("/{id}")
    public DispositivosUsuario obtenerDispositivoUsuario(@PathVariable Long id) {
        return service.obtenerDispositivosUsuario(id);
    }

    @GetMapping
    public List<DispositivosUsuario> listarDispositivosUsuario() {
        return service.listarDispositivoUsuario();
    }

    @PutMapping("/{id}")
    public DispositivosUsuario actualizarDispositivoUsuario(@PathVariable Long id, @RequestBody DispositivosUsuario dispositivoUsuario) {
        return service.actualizarDispositivosUsuario(id, dispositivoUsuario);
    }

    @DeleteMapping("/{id}")
    public void eliminarDispositivoUsuario(@PathVariable Long id) {
        service.eliminarDispositivosUsuario(id);
    }
}
