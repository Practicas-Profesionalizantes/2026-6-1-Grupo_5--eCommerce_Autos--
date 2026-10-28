package com.back.carfind.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.back.carfind.model.TarjetasUsuario;
import com.back.carfind.service.TarjetasUsuarioService;

@RestController
@RequestMapping("/api/tarjetas-usuario")
@CrossOrigin(origins = "*")
public class TarjetasUsuarioController {

    private final TarjetasUsuarioService service;

    public TarjetasUsuarioController(TarjetasUsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public TarjetasUsuario crearTarjetaUsuario(@RequestBody TarjetasUsuario tarjetaUsuario) {
        return service.crearTarjetaUsuario(tarjetaUsuario);
    }

    @GetMapping("/{id}")
    public TarjetasUsuario obtenerTarjetaUsuario(@PathVariable Long id) {
        return service.obtenerTarjetaUsuario(id);
    }

    @GetMapping
    public List<TarjetasUsuario> listarTarjetasUsuario() {
        return service.listarTarjetasUsuario();
    }

    @PutMapping("/{id}")
    public TarjetasUsuario actualizarTarjetaUsuario(@PathVariable Long id, @RequestBody TarjetasUsuario tarjetaUsuario) {
        return service.actualizarTarjetaUsuario(id, tarjetaUsuario);
    }

    @DeleteMapping("/{id}")
    public void eliminarTarjetaUsuario(@PathVariable Long id) {
        service.eliminarTarjetaUsuario(id);
    }
}
