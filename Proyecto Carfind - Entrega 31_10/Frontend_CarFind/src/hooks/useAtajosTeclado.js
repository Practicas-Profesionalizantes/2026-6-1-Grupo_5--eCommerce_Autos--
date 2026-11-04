import { useEffect, useSyncExternalStore } from 'react';
import { useNavigate } from 'react-router-dom';
import { obtenerPreferenciasUI, suscribirPreferenciasUI } from '../utils/preferenciasUI';

/** Atajos de una sola tecla (Configuración > Accesibilidad > "Claves de caracteres"). */
const RUTAS = { h: '/', w: '/wishlist', p: '/perfil', c: '/configuracion' };

/**
 * Activa los atajos de teclado globales cuando el usuario los habilitó.
 * No actúan mientras se escribe en un campo ni con Ctrl / Alt / Cmd apretados.
 *   H inicio · W lista de deseos · P perfil · C configuración · / buscador
 */
export default function useAtajosTeclado() {
  const navigate = useNavigate();
  const { atajosTeclado } = useSyncExternalStore(suscribirPreferenciasUI, obtenerPreferenciasUI);

  useEffect(() => {
    if (!atajosTeclado) return undefined;

    const onKeyDown = (e) => {
      if (e.ctrlKey || e.metaKey || e.altKey || e.repeat || e.defaultPrevented) return;
      const el = e.target;
      const escribiendo =
        el instanceof HTMLElement && (el.isContentEditable || ['INPUT', 'TEXTAREA', 'SELECT'].includes(el.tagName));
      if (escribiendo) return;

      if (e.key === '/') {
        const buscador = document.querySelector('.search-bar');
        if (buscador) {
          e.preventDefault();
          buscador.focus();
        }
        return;
      }
      const ruta = RUTAS[e.key.toLowerCase()];
      if (ruta) navigate(ruta);
    };

    document.addEventListener('keydown', onKeyDown);
    return () => document.removeEventListener('keydown', onKeyDown);
  }, [atajosTeclado, navigate]);
}
