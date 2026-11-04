import autoGenerico from '../assets/images/auto.jpg';

/** Foto a mostrar para una publicación: la primera subida o, si no tiene, una imagen genérica. */
export const imagenAuto = (url) => url || autoGenerico;

/** Para <img onError>: si la URL guardada no carga, muestra la imagen genérica (una sola vez). */
export const usarImagenGenerica = (e) => {
  if (e.currentTarget.src !== new URL(autoGenerico, window.location.href).href) e.currentTarget.src = autoGenerico;
};
