import React, { useState } from 'react';
import { useOutletContext } from 'react-router-dom';
import SeccionConfig from '../../components/configuracion/SeccionConfig';
import { ANIMACIONES, SUBRAYADO_ENLACES } from '../../types/configuracion';

const AYUDA = [
  { titulo: 'Atajos de teclado', texto: 'Con "Claves de caracteres" activado podés navegar con una sola tecla: H inicio, W lista de deseos, P perfil, C configuración y / para ir al buscador. No funcionan mientras escribís en un campo.' },
  { titulo: 'Movimiento', texto: 'Controla las animaciones y transiciones de la página. "Sincronizar con el sistema" respeta la opción de reducir movimiento de tu dispositivo.' },
  { titulo: 'Contenido', texto: 'Elegí si los enlaces se muestran subrayados para distinguirlos mejor del resto del texto.' },
  { titulo: 'Botones de guardar', texto: 'Cada bloque se guarda por separado, con su propio botón.' },
];

/** Estado de un bloque: qué eligió el usuario todavía sin guardar y cómo salió el último guardado. */
function useBloque(inicial) {
  const [borrador, setBorrador] = useState(inicial);
  const [estado, setEstado] = useState(null); // { tipo: 'ok' | 'error', texto }
  const [guardando, setGuardando] = useState(false);
  return { borrador, setBorrador, estado, setEstado, guardando, setGuardando };
}

function Accesibilidad() {
  const { config, guardarAccesibilidadCambios } = useOutletContext();
  const actual = config.accesibilidad;

  const atajos = useBloque(actual.atajosTeclado);
  const movimiento = useBloque(actual.animaciones);
  const contenido = useBloque(actual.subrayadoEnlaces);

  const guardar = (bloque, campo) => async (e) => {
    e.preventDefault();
    bloque.setGuardando(true);
    bloque.setEstado(null);
    const res = await guardarAccesibilidadCambios({ ...actual, [campo]: bloque.borrador });
    bloque.setGuardando(false);
    bloque.setEstado(res.ok ? { tipo: 'ok', texto: 'Preferencias guardadas.' } : { tipo: 'error', texto: res.error });
  };

  // Función común (no un componente definido acá adentro): se llama como {estadoBloque(bloque)}.
  const estadoBloque = (bloque) =>
    bloque.estado ? (
      <p className={bloque.estado.tipo === 'ok' ? 'config-ok' : 'perfil-error'} role={bloque.estado.tipo === 'ok' ? 'status' : 'alert'}>
        {bloque.estado.texto}
      </p>
    ) : null;

  return (
    <SeccionConfig titulo="Accesibilidad" ayuda={AYUDA}>
      <form className="config-bloque" onSubmit={guardar(atajos, 'atajosTeclado')}>
        <h2 className="config-bloque-titulo">Atajos de teclado</h2>
        <p className="config-ayuda-texto">General</p>
        <label className="config-opcion">
          <input type="checkbox" checked={atajos.borrador} onChange={(e) => atajos.setBorrador(e.target.checked)} />
          Claves de caracteres
        </label>
        <button type="submit" className="btn-perfil btn-perfil--ghost" disabled={atajos.guardando}>
          Guardar preferencias de accesos directos al teclado
        </button>
        {estadoBloque(atajos)}
      </form>

      <form className="config-bloque" onSubmit={guardar(movimiento, 'animaciones')}>
        <h2 className="config-bloque-titulo">Movimiento</h2>
        <p className="config-ayuda-texto">Reproducción automática de imágenes animadas</p>
        <div role="radiogroup" aria-label="Movimiento" className="config-opciones">
          {ANIMACIONES.map((o) => (
            <label key={o.id} className="config-opcion">
              <input type="radio" name="animaciones" value={o.id} checked={movimiento.borrador === o.id} onChange={() => movimiento.setBorrador(o.id)} />
              {o.nombre}
            </label>
          ))}
        </div>
        <button type="submit" className="btn-perfil btn-perfil--ghost" disabled={movimiento.guardando}>
          Guardar preferencias de movimiento
        </button>
        {estadoBloque(movimiento)}
      </form>

      <form className="config-bloque" onSubmit={guardar(contenido, 'subrayadoEnlaces')}>
        <h2 className="config-bloque-titulo">Contenido</h2>
        <p className="config-ayuda-texto">Subrayado del enlace</p>
        <div role="radiogroup" aria-label="Subrayado del enlace" className="config-opciones">
          {SUBRAYADO_ENLACES.map((o) => (
            <label key={o.id} className="config-opcion">
              <input type="radio" name="subrayadoEnlaces" value={o.id} checked={contenido.borrador === o.id} onChange={() => contenido.setBorrador(o.id)} />
              {o.nombre}
            </label>
          ))}
        </div>
        <button type="submit" className="btn-perfil btn-perfil--ghost" disabled={contenido.guardando}>
          Guardar preferencias de contenido
        </button>
        {estadoBloque(contenido)}
      </form>
    </SeccionConfig>
  );
}

export default Accesibilidad;
