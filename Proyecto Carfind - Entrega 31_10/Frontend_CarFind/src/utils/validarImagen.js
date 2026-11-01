import { FOTO_MAX_BYTES, FOTO_EXTENSIONES } from '../types/perfil';

/**
 * Valida que el archivo elegido sea una imagen y no supere el tamaño máximo.
 * El backend debe volver a validar ambas cosas.
 *
 * @param {File|undefined} archivo
 * @returns {string|null} Mensaje de error, o null si el archivo es válido.
 */
export function validarImagen(archivo) {
  if (!archivo) return 'No seleccionaste ningún archivo.';

  const extension = archivo.name.split('.').pop().toLowerCase();
  // Si el navegador informa el tipo, manda el tipo; si viene vacío, se usa la extensión.
  const esImagen = archivo.type ? archivo.type.startsWith('image/') : FOTO_EXTENSIONES.includes(extension);

  if (!esImagen) {
    return 'Ese archivo no es una imagen. Elegí un archivo .jpg, .png, .heic o similar.';
  }
  if (archivo.size > FOTO_MAX_BYTES) {
    const maxMb = FOTO_MAX_BYTES / (1024 * 1024);
    return `La imagen supera el máximo de ${maxMb} MB. Elegí una más liviana.`;
  }
  return null;
}
