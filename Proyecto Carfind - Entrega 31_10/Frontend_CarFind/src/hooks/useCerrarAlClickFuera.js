import { useEffect } from 'react';

/**
 * Cierra un cuadro flotante con Escape o con un clic fuera de él.
 * @param {React.RefObject<HTMLElement|null>} ref  Contenedor del cuadro (y de su botón)
 * @param {boolean} abierto
 * @param {() => void} cerrar
 */
export default function useCerrarAlClickFuera(ref, abierto, cerrar) {
  useEffect(() => {
    if (!abierto) return undefined;

    const onKeyDown = (e) => {
      if (e.key === 'Escape') cerrar();
    };
    const onMouseDown = (e) => {
      if (ref.current && !ref.current.contains(e.target)) cerrar();
    };
    document.addEventListener('keydown', onKeyDown);
    document.addEventListener('mousedown', onMouseDown);
    return () => {
      document.removeEventListener('keydown', onKeyDown);
      document.removeEventListener('mousedown', onMouseDown);
    };
  }, [ref, abierto, cerrar]);
}
