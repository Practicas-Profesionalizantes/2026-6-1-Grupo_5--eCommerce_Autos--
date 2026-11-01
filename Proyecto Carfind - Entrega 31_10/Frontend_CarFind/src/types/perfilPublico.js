/**
 * Contrato de datos de la vista Perfil Ajeno (perfil público de otro usuario).
 * Reutiliza la forma de PublicacionPerfilItem de types/perfil.js.
 */

/**
 * Datos públicos de un usuario.
 * @typedef {Object} PerfilPublico
 * @property {number|string} id
 * @property {string}        nombre
 * @property {string}        apellido
 * @property {string}        ubicacion
 * @property {number}        reputacion      Valoración de 0 a 5 (se dibuja con estrellas)
 * @property {string}        fechaCreacion   Fecha ISO (ej: "2025-03-14")
 * @property {string}        telefono
 * @property {string|null}   fotoUrl         null si no tiene foto de perfil
 */

/**
 * Respuesta de GET /usuarios/:id.
 * El "número de publicaciones" no viene aparte: se cuenta sobre `publicaciones`.
 * @typedef {Object} PerfilPublicoResponse
 * @property {PerfilPublico} usuario
 * @property {import('./perfil').PublicacionPerfilItem[]} publicaciones
 */

/** Cantidad de estrellas de la valoración. */
export const ESTRELLAS_MAXIMAS = 5;
