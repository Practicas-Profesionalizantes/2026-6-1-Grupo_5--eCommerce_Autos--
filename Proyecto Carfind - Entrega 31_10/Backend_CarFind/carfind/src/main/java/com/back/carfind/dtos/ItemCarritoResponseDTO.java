package com.back.carfind.dtos;

/**
 * Datos que devuelve la API de un registro de la tabla item_carrito.
 */
public class ItemCarritoResponseDTO {

    private Long usuarioId;
    private Long publicacionId;

    public ItemCarritoResponseDTO() {
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getPublicacionId() {
        return publicacionId;
    }

    public void setPublicacionId(Long publicacionId) {
        this.publicacionId = publicacionId;
    }
}
