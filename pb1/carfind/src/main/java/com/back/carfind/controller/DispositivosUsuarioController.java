package com.back.carfind.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.back.carfind.model.DispositivosUsuario;
import com.back.carfind.service.DispositivosUsuarioService;

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
        return service.crearDispositivoUsuario(dispositivoUsuario);
    }

    @GetMapping("/{id}")
    public DispositivosUsuario obtenerDispositivoUsuario(@PathVariable Long id) {
        return service.obtenerDispositivoUsuario(id);
    }

    @GetMapping
    public List<DispositivosUsuario> listarDispositivosUsuario() {
        return service.listarDispositivosUsuario();
    }

    @PutMapping("/{id}")
    public DispositivosUsuario actualizarDispositivoUsuario(@PathVariable Long id, @RequestBody DispositivosUsuario dispositivoUsuario) {
        return service.actualizarDispositivoUsuario(id, dispositivoUsuario);
    }

    @DeleteMapping("/{id}")
    public void eliminarDispositivoUsuario(@PathVariable Long id) {
        service.eliminarDispositivoUsuario(id);
    }
}
