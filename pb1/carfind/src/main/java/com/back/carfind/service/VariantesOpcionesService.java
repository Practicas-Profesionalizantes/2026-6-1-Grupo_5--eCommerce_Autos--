package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.VariantesOpciones;
import com.back.carfind.repository.VariantesOpcionesRepository;

@Service
public class VariantesOpcionesService {

    @Autowired
    private VariantesOpcionesRepository repository;

    public VariantesOpciones crearVarianteOpcion(
            VariantesOpciones varianteOpcion) {

        return repository.save(varianteOpcion);
    }

    public VariantesOpciones obtenerVarianteOpcion(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Variante u opción no encontrada"
                ));
    }

    public List<VariantesOpciones> listarVariantesOpciones() {
        return repository.findAll();
    }

    public VariantesOpciones actualizarVarianteOpcion(
            Long id,
            VariantesOpciones datos) {

        VariantesOpciones varianteOpcion =
                obtenerVarianteOpcion(id);

        varianteOpcion.setValor(datos.getValor());

        return repository.save(varianteOpcion);
    }

    public void eliminarVarianteOpcion(Long id) {
        VariantesOpciones varianteOpcion =
                obtenerVarianteOpcion(id);

        repository.delete(varianteOpcion);
    }
}