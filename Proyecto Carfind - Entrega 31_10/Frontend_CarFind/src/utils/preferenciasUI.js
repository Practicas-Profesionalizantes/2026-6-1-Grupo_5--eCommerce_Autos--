import { ESCALA_FUENTE, FUENTES } from '../types/configuracion';

/**
 * Preferencias de interfaz que se aplican directamente al documento:
 * tamaño y tipo de fuente, atajos de teclado, animaciones y subrayado de enlaces.
 * (El tema claro/oscuro vive aparte, en utils/tema.js.)
 *
 * Se guardan en localStorage para aplicarse apenas carga la app, antes de que
 * React dibuje, y evitar un "salto" visual. Si hay backend, la fuente de verdad
 * es el servidor (ver useConfiguracion) y esto funciona como caché local.
 */
const CLAVE = 'preferenciasUI';

export const PREFERENCIAS_UI_POR_DEFECTO = {
  escalaFuente: ESCALA_FUENTE.defecto,
  fuente: 'predeterminada',
  atajosTeclado: false,
  animaciones: 'sistema',
  subrayadoEnlaces: 'mostrar',
};

/** Descarta valores inválidos (por ejemplo, un localStorage editado a mano). */
function limpiar(crudo) {
  const p = { ...PREFERENCIAS_UI_POR_DEFECTO, ...crudo };
  const escala = Number(p.escalaFuente);
  return {
    escalaFuente: Number.isFinite(escala)
      ? Math.min(ESCALA_FUENTE.max, Math.max(ESCALA_FUENTE.min, escala))
      : ESCALA_FUENTE.defecto,
    fuente: FUENTES.some((f) => f.id === p.fuente) ? p.fuente : 'predeterminada',
    atajosTeclado: p.atajosTeclado === true,
    animaciones: ['sistema', 'activado', 'desactivado'].includes(p.animaciones) ? p.animaciones : 'sistema',
    subrayadoEnlaces: p.subrayadoEnlaces === 'ocultar' ? 'ocultar' : 'mostrar',
  };
}

function leer() {
  try {
    const crudo = localStorage.getItem(CLAVE);
    return limpiar(crudo ? JSON.parse(crudo) : {});
  } catch {
    // Sin acceso a localStorage o JSON roto: se usan los valores por defecto.
    return limpiar({});
  }
}

let estado = leer();
const oyentes = new Set();

/** Pinta las preferencias en el <html>; el CSS (preferencias.css) hace el resto. */
function aplicar(p) {
  const raiz = document.documentElement;
  raiz.style.fontSize = p.escalaFuente === ESCALA_FUENTE.defecto ? '' : `${p.escalaFuente}%`;

  if (p.fuente === 'predeterminada') {
    raiz.removeAttribute('data-fuente');
    raiz.style.removeProperty('--fuente-app');
  } else {
    const fuente = FUENTES.find((f) => f.id === p.fuente);
    raiz.setAttribute('data-fuente', p.fuente);
    raiz.style.setProperty('--fuente-app', fuente.stack);
  }
  raiz.setAttribute('data-animaciones', p.animaciones);
  raiz.setAttribute('data-subrayado', p.subrayadoEnlaces);
}

/** Valor actual (la referencia solo cambia cuando cambian las preferencias). */
export const obtenerPreferenciasUI = () => estado;

/** Para useSyncExternalStore. */
export function suscribirPreferenciasUI(oyente) {
  oyentes.add(oyente);
  return () => oyentes.delete(oyente);
}

/**
 * Aplica y guarda un cambio (parcial) en las preferencias.
 * @param {Partial<typeof PREFERENCIAS_UI_POR_DEFECTO>} parcial
 */
export function guardarPreferenciasUI(parcial) {
  estado = limpiar({ ...estado, ...parcial });
  aplicar(estado);
  try {
    localStorage.setItem(CLAVE, JSON.stringify(estado));
  } catch {
    // Sin acceso a localStorage: las preferencias duran solo mientras la pestaña esté abierta.
  }
  oyentes.forEach((oyente) => oyente());
}

/** Aplica lo guardado. Se llama en main.jsx antes de renderizar. */
export function aplicarPreferenciasGuardadas() {
  aplicar(estado);
}
