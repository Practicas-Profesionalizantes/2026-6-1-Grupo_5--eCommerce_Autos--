import React from 'react';
import { useOutletContext } from 'react-router-dom';
import SeccionConfig from '../../components/configuracion/SeccionConfig';
import ToggleSwitch from '../../components/ToggleSwitch';
import { TIPOS_AVISO, FRECUENCIAS_CORREO } from '../../types/configuracion';

const AYUDA = [
  { titulo: 'Recibir avisos por', texto: 'Elegí si los avisos te llegan a la página (campana y mensajería), por correo electrónico o por ambos. Si apagás un canal, se desactiva su columna en la tabla.' },
  { titulo: 'Qué querés que te avisemos', texto: 'Marcá, para cada tipo de aviso, por qué canal querés recibirlo.' },
  { titulo: 'Frecuencia del correo', texto: 'Recibí los correos al instante o agrupados en un resumen diario o semanal.' },
  { titulo: 'No molestar', texto: 'Silencia los avisos entre las horas que elijas. Cuando termina la franja, los avisos vuelven a llegar.' },
  { titulo: 'Guardado', texto: 'Cada cambio se guarda solo; no hace falta ningún botón.' },
];

function Notificaciones() {
  const { config, actualizarNotificaciones } = useOutletContext();
  const n = config.notificaciones;

  const cambiarCanal = (canal, valor) => actualizarNotificaciones({ ...n, canales: { ...n.canales, [canal]: valor } });
  const cambiarAviso = (id, canal, valor) =>
    actualizarNotificaciones({ ...n, avisos: { ...n.avisos, [id]: { ...n.avisos[id], [canal]: valor } } });
  const cambiarNoMolestar = (parcial) => actualizarNotificaciones({ ...n, noMolestar: { ...n.noMolestar, ...parcial } });

  return (
    <SeccionConfig titulo="Notificaciones" ayuda={AYUDA}>
      <section className="config-bloque">
        <h2 className="config-bloque-titulo">Recibir avisos por</h2>
        <div className="config-canales">
          <div className="config-tarjeta">
            <span>En la página (campana y mensajería)</span>
            <ToggleSwitch checked={n.canales.app} onChange={(v) => cambiarCanal('app', v)} label="Recibir avisos en la página" />
          </div>
          <div className="config-tarjeta">
            <span>Correo electrónico</span>
            <ToggleSwitch checked={n.canales.correo} onChange={(v) => cambiarCanal('correo', v)} label="Recibir avisos por correo electrónico" />
          </div>
        </div>
      </section>

      <section className="config-bloque">
        <h2 className="config-bloque-titulo">Qué querés que te avisemos</h2>
        <div className="config-tabla-contenedor">
          <table className="config-tabla">
            <thead>
              <tr>
                <th scope="col">Aviso</th>
                <th scope="col" className="config-col-check">En la página</th>
                <th scope="col" className="config-col-check">Por correo</th>
              </tr>
            </thead>
            <tbody>
              {TIPOS_AVISO.map(({ id, nombre }) => (
                <tr key={id}>
                  <th scope="row" className="config-aviso-nombre">{nombre}</th>
                  <td className="config-col-check">
                    <input
                      type="checkbox"
                      checked={n.avisos[id].app}
                      disabled={!n.canales.app}
                      onChange={(e) => cambiarAviso(id, 'app', e.target.checked)}
                      aria-label={`${nombre}: en la página`}
                    />
                  </td>
                  <td className="config-col-check">
                    <input
                      type="checkbox"
                      checked={n.avisos[id].correo}
                      disabled={!n.canales.correo}
                      onChange={(e) => cambiarAviso(id, 'correo', e.target.checked)}
                      aria-label={`${nombre}: por correo`}
                    />
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </section>

      <section className="config-bloque">
        <h2 className="config-bloque-titulo">Frecuencia del correo</h2>
        <div role="radiogroup" aria-label="Frecuencia del correo" className="config-opciones config-opciones--fila">
          {FRECUENCIAS_CORREO.map((f) => (
            <label key={f.id} className="config-opcion">
              <input
                type="radio"
                name="frecuenciaCorreo"
                value={f.id}
                checked={n.frecuenciaCorreo === f.id}
                disabled={!n.canales.correo}
                onChange={() => actualizarNotificaciones({ ...n, frecuenciaCorreo: f.id })}
              />
              {f.nombre}
            </label>
          ))}
        </div>

        <div className="config-no-molestar">
          <ToggleSwitch checked={n.noMolestar.activo} onChange={(v) => cambiarNoMolestar({ activo: v })} label="Silenciar avisos en un horario" />
          <span>Silenciar avisos de</span>
          <input
            type="time"
            className="perfil-form-input config-hora"
            value={n.noMolestar.desde}
            disabled={!n.noMolestar.activo}
            onChange={(e) => e.target.value && cambiarNoMolestar({ desde: e.target.value })}
            aria-label="Silenciar desde"
          />
          <span>a</span>
          <input
            type="time"
            className="perfil-form-input config-hora"
            value={n.noMolestar.hasta}
            disabled={!n.noMolestar.activo}
            onChange={(e) => e.target.value && cambiarNoMolestar({ hasta: e.target.value })}
            aria-label="Silenciar hasta"
          />
        </div>
      </section>
    </SeccionConfig>
  );
}

export default Notificaciones;
