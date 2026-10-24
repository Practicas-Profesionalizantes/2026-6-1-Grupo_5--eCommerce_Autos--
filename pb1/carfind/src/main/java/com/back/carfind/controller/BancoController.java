package com.back.carfind.controller;

import com.back.carfind.model.Banco;
import com.back.carfind.service.BancoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bancos")
@CrossOrigin(origins = "*")
public class BancoController {

    private final BancoService service;

    public BancoController(BancoService service) {
        this.service = service;
    }

    @PostMapping
    public Banco crearBanco(@RequestBody Banco banco) {
        return service.crearBanco(banco);
    }

    @GetMapping("/{id}")
    public Banco obtenerBanco(@PathVariable Long id) {
        return service.obtenerBanco(id);
    }

    @GetMapping
    public List<Banco> listarBancos() {
        return service.listarBanco();
    }

    @PutMapping("/{id}")
    public Banco actualizarBanco(@PathVariable Long id, @RequestBody Banco banco) {
        return service.actualizarBanco(id, banco);
    }

    @DeleteMapping("/{id}")
    public void eliminarBanco(@PathVariable Long id) {
        service.eliminarbanco(id);
    }
}
