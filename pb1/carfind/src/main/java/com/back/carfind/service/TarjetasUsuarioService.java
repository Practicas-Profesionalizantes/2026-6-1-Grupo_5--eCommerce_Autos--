package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.TarjetasUsuario;
import com.back.carfind.repository.TarjetasUsuarioRepository;

@Service
public class TarjetasUsuarioService {

    @Autowired
    private TarjetasUsuarioRepository repository;

    public TarjetasUsuario crearTarjetaUsuario(
            TarjetasUsuario tarjetaUsuario) {

        return repository.save(tarjetaUsuario);
    }

    public TarjetasUsuario obtenerTarjetaUsuario(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Tarjeta de usuario no encontrada"
                ));
    }

    public List<TarjetasUsuario> listarTarjetasUsuario() {
        return repository.findAll();
    }

    public TarjetasUsuario actualizarTarjetaUsuario(
            Long id,
            TarjetasUsuario datos) {

        TarjetasUsuario tarjetaUsuario =
                obtenerTarjetaUsuario(id);

        tarjetaUsuario.setMarca(datos.getMarca());
        tarjetaUsuario.setUltimos4(datos.getUltimos4());
        tarjetaUsuario.setToken(datos.getToken());
        tarjetaUsuario.setVencimiento_mes(
                datos.getVencimiento_mes()
        );
        tarjetaUsuario.setVencmiento_anio(
                datos.getVencmiento_anio()
        );

        return repository.save(tarjetaUsuario);
    }

    public void eliminarTarjetaUsuario(Long id) {
        TarjetasUsuario tarjetaUsuario =
                obtenerTarjetaUsuario(id);

        repository.delete(tarjetaUsuario);
    }
}