/**
 * Contrato de datos de la vista Perfil.
 *
 * Igual que en la Wishlist, los tipos se definen con JSDoc. Este es el
 * contrato que el backend debe respetar en sus respuestas.
 */

/**
 * Datos del usuario (tabla Usuario).
 * @typedef {Object} Perfil
 * @property {number|string} id
 * @property {string}        nombre
 * @property {string}        apellido
 * @property {string}        ubicacion
 * @property {number}        reputacion      Valor numérico tomado tal cual de la tabla Usuario
 * @property {string}        fechaCreacion   Fecha ISO (ej: "2025-03-14")
 * @property {string}        telefono
 * @property {string|null}   fotoUrl         null si el usuario no tiene foto de perfil
 */

/**
 * Publicación del usuario (tabla Publicaciones).
 * @typedef {Object} PublicacionPerfilItem
 * @property {number|string} id
 * @property {string}        titulo
 * @property {string}        imagenUrl     Foto del auto
 * @property {boolean}       disponible    true = Disponible, false = no disponible
 * @property {'ACTIVA'|'PAUSADA'|'VENDIDA'|'CANCELADA'} [estado]  Estado de la publicación (pausada = oculta para los demás)
 * @property {number}        likes
 * @property {number}        comentarios
 * @property {number}        compartidos
 */

/**
 * Respuesta de GET /perfil.
 * El "número de publicaciones" no viene aparte: se cuenta sobre `publicaciones`.
 * @typedef {Object} PerfilResponse
 * @property {Perfil} usuario
 * @property {PublicacionPerfilItem[]} publicaciones
 */

/**
 * Campos editables desde "Editar perfil".
 * @typedef {Object} PerfilEditable
 * @property {string} nombre
 * @property {string} apellido
 * @property {string} ubicacion
 * @property {string} telefono
 */

/** Tamaño máximo de la foto de perfil. Valor sugerido: a confirmar con el equipo. */
export const FOTO_MAX_BYTES = 5 * 1024 * 1024;

/** Extensiones aceptadas cuando el navegador no informa el tipo MIME (pasa a veces con .heic). */
export const FOTO_EXTENSIONES = ['jpg', 'jpeg', 'png', 'gif', 'webp', 'heic', 'heif'];
