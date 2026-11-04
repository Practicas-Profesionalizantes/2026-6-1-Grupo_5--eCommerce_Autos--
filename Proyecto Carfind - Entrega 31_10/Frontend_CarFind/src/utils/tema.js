/**
 * Tema de la app. Tres modos: 'sistema' (sigue al sistema operativo), 'oscuro' y 'claro'.
 * La fuente de verdad visual es el atributo data-theme del <html> (lo leen las variables CSS)
 * y el modo elegido se guarda en localStorage para recordarlo al recargar.
 *
 * Sin nada guardado, el modo es 'sistema' (el predeterminado de Configuración > Apariencia).
 */
const CLAVE_TEMA = 'theme';

// Valores guardados en localStorage ↔ modos de la app.
const GUARDADO_A_MODO = { system: 'sistema', dark: 'oscuro', light: 'claro' };
const MODO_A_GUARDADO = { sistema: 'system', oscuro: 'dark', claro: 'light' };

const oyentes = new Set();
let modo = leerModoGuardado();
let dejarDeEscucharSistema = null;

function leerModoGuardado() {
  try {
    return GUARDADO_A_MODO[localStorage.getItem(CLAVE_TEMA)] ?? 'sistema';
  } catch {
    return 'sistema';
  }
}

function pintar(oscuro) {
  if (oscuro) {
    document.documentElement.setAttribute('data-theme', 'dark');
  } else {
    document.documentElement.removeAttribute('data-theme');
  }
}

const avisar = () => oyentes.forEach((oyente) => oyente());

function activarModo(nuevoModo) {
  modo = nuevoModo;
  dejarDeEscucharSistema?.();
  dejarDeEscucharSistema = null;

  if (modo === 'sistema' && typeof window.matchMedia === 'function') {
    const consulta = window.matchMedia('(prefers-color-scheme: dark)');
    pintar(consulta.matches);
    const alCambiar = (e) => {
      pintar(e.matches);
      avisar();
    };
    consulta.addEventListener('change', alCambiar);
    dejarDeEscucharSistema = () => consulta.removeEventListener('change', alCambiar);
  } else {
    pintar(modo === 'oscuro');
  }
  avisar();
}

/** @returns {boolean} true si el tema oscuro está aplicado ahora (ya resuelto, aunque el modo sea 'sistema'). */
export const esTemaOscuro = () => document.documentElement.getAttribute('data-theme') === 'dark';

/** @returns {'sistema'|'oscuro'|'claro'} El modo elegido por el usuario. */
export const obtenerModoTema = () => modo;

/** Aplica y guarda un modo. @param {'sistema'|'oscuro'|'claro'} nuevoModo */
export function aplicarModoTema(nuevoModo) {
  try {
    localStorage.setItem(CLAVE_TEMA, MODO_A_GUARDADO[nuevoModo]);
  } catch {
    // Sin acceso a localStorage: el tema solo dura mientras la pestaña esté abierta.
  }
  activarModo(nuevoModo);
}

/** Atajo para el botón del Header: elige claro u oscuro de forma explícita. @param {boolean} oscuro */
export const aplicarTema = (oscuro) => aplicarModoTema(oscuro ? 'oscuro' : 'claro');

/** Aplica el modo guardado. Se llama antes de renderizar para evitar un destello de tema claro. */
export function aplicarTemaGuardado() {
  activarModo(modo);
}

/** Para useSyncExternalStore: avisa cuando cambia el tema (desde el Header, Configuración o el sistema). */
export function suscribirTema(oyente) {
  oyentes.add(oyente);
  return () => oyentes.delete(oyente);
}
