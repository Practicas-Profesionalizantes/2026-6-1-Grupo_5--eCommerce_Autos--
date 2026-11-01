/**
 * Contrato de datos de la Wishlist (Lista de Deseos).
 *
 * El proyecto está en JS/JSX, por eso los tipos se definen con JSDoc
 * (VS Code los reconoce igual que TypeScript). Si el equipo migra a TS,
 * basta con convertir estos typedef en `interface` dentro de types/wishlist.ts.
 *
 * Este es el contrato que el backend debe respetar en sus respuestas.
 */

/** @typedef {'Nuevo' | 'Usado'} EstadoVehiculo */
/** @typedef {'NAFTA' | 'DIESEL' | 'GNC' | 'ELECTRICO' | 'HIBRIDO'} Combustible */

/**
 * Publicación guardada en la wishlist.
 * @typedef {Object} WishlistItem
 * @property {number|string} id          ID de la publicación
 * @property {string}        titulo      Ej: "Toyota Corolla Negro - 2023"
 * @property {EstadoVehiculo} estado     Nuevo / Usado
 * @property {number}        kilometraje Kilómetros recorridos (ej: 10000)
 * @property {Combustible}   combustible Tipo de combustible (ej: "NAFTA")
 * @property {string}        imagenUrl   URL de la primera imagen subida
 * @property {number}        precio      Precio numérico (sin formato)
 */

/**
 * Fila del resumen de compra.
 * @typedef {Object} ResumenItem
 * @property {number|string} id
 * @property {string}        titulo
 * @property {number}        precio
 */

/**
 * Resumen de compra.
 * @typedef {Object} WishlistResumen
 * @property {ResumenItem[]} items
 * @property {number}        total   Suma de los precios de los ítems
 * @property {string}        moneda  Ej: "ARS"
 */

/**
 * Respuesta completa de GET /wishlist.
 * @typedef {Object} WishlistResponse
 * @property {WishlistItem[]} items
 * @property {WishlistResumen} resumen
 */

/**
 * Respuesta de POST /wishlist y DELETE /wishlist/:id.
 * @typedef {Object} WishlistMutationResponse
 * @property {boolean} ok
 * @property {number|string} itemId
 * @property {WishlistResponse} wishlist  Estado actualizado de la wishlist
 */

export const ESTADOS_VEHICULO = ['Nuevo', 'Usado'];
export const MONEDA_DEFAULT = 'ARS';
