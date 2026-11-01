import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import Modal from '../Modal';
import { formatearFechaEnvio } from '../../utils/formatearFecha';
import { nombreCompleto, nombreEstadoReporte, nombreMotivo, nombreTipoReporte } from '../../utils/reportesAdmin';
import { RESPUESTA_MAX_CARACTERES } from '../../types/admin';

/**
 * Detalle de un reporte recibido: quién lo envió, qué reportó, historial de respuestas y formulario para responder.
 * @param {{
 *   reporte: import('../../types/admin').ReporteRecibido,
 *   onResponder: (id: number|string, datos: { texto: string, estado?: string }) => Promise<{ ok: boolean, error?: string }>,
 *   onCambiarEstado: (id: number|string, estado: string) => Promise<{ ok: boolean, error?: string }>,
 *   onCerrar: () => void,
 * }} props
 */
function DetalleReporteModal({ reporte, onResponder, onCambiarEstado, onCerrar }) {
  const [texto, setTexto] = useState('');
  const [marcarResuelto, setMarcarResuelto] = useState(true);
  const [enviando, setEnviando] = useState(false);
  const [error, setError] = useState(null);

  const resuelto = reporte.estado === 'resuelto';

  const enviarRespuesta = async (e) => {
    e.preventDefault();
    if (!texto.trim()) return;
    setEnviando(true);
    setError(null);
    const res = await onResponder(reporte.id, {
      texto: texto.trim(),
      ...(marcarResuelto && !resuelto ? { estado: 'resuelto' } : {}),
    });
    setEnviando(false);
    if (res.ok) setTexto('');
    else setError(res.error);
  };

  const cambiarEstado = async (estado) => {
    setEnviando(true);
    setError(null);
    const res = await onCambiarEstado(reporte.id, estado);
    setEnviando(false);
    if (!res.ok) setError(res.error);
  };

  return (
    <Modal titulo={`Reporte #${reporte.id}`} onCerrar={onCerrar} ancho>
      <div className="admin-detalle">
        <div className="admin-detalle-cabecera">
          <span className={`estado-badge estado-badge--${reporte.estado}`}>{nombreEstadoReporte(reporte.estado)}</span>
          <span>{nombreTipoReporte(reporte.tipo)}</span>
          <time dateTime={reporte.fecha}>{formatearFechaEnvio(reporte.fecha)}</time>
        </div>

        <dl className="admin-detalle-datos">
          <div>
            <dt>Reportado por</dt>
            <dd><Link to={`/usuario/${reporte.reportante.id}`}>{nombreCompleto(reporte.reportante)}</Link></dd>
          </div>
          {reporte.publicacion && (
            <div>
              <dt>Publicación</dt>
              <dd><Link to={`/publicacion/${reporte.publicacion.id}`}>{reporte.publicacion.titulo}</Link></dd>
            </div>
          )}
          {reporte.motivo && (
            <div>
              <dt>Motivo</dt>
              <dd>{nombreMotivo(reporte.motivo)}</dd>
            </div>
          )}
          {reporte.usuarioReportado && (
            <div>
              <dt>Usuario reportado</dt>
              <dd><Link to={`/usuario/${reporte.usuarioReportado.id}`}>{nombreCompleto(reporte.usuarioReportado)}</Link></dd>
            </div>
          )}
        </dl>

        <section>
          <h3 className="admin-detalle-subtitulo">Mensaje del usuario</h3>
          <p className="admin-detalle-texto">{reporte.descripcion || 'El usuario no escribió ninguna descripción.'}</p>
        </section>

        {reporte.respuestas.length > 0 && (
          <section>
            <h3 className="admin-detalle-subtitulo">Respuestas enviadas</h3>
            <ul className="admin-respuestas">
              {reporte.respuestas.map((r) => (
                <li key={r.id} className="admin-respuesta">
                  <p className="admin-detalle-texto">{r.texto}</p>
                  <small>{r.autor} · {formatearFechaEnvio(r.fecha)}</small>
                </li>
              ))}
            </ul>
          </section>
        )}

        <form className="admin-detalle-form" onSubmit={enviarRespuesta}>
          <label htmlFor="admin-respuesta" className="reporte-etiqueta">Responder al usuario</label>
          <textarea
            id="admin-respuesta"
            className="reporte-textarea"
            rows={4}
            value={texto}
            maxLength={RESPUESTA_MAX_CARACTERES}
            onChange={(e) => setTexto(e.target.value)}
          />
          <span className="reporte-contador">{`${texto.length}/${RESPUESTA_MAX_CARACTERES}`}</span>

          {!resuelto && (
            <label className="config-opcion">
              <input type="checkbox" checked={marcarResuelto} onChange={(e) => setMarcarResuelto(e.target.checked)} />
              Marcar el reporte como resuelto al enviar
            </label>
          )}

          {error && <p className="perfil-error" role="alert">{error}</p>}

          <div className="admin-detalle-acciones">
            {reporte.estado === 'pendiente' && (
              <button type="button" className="btn-perfil btn-perfil--ghost" disabled={enviando} onClick={() => cambiarEstado('en_revision')}>
                Marcar en revisión
              </button>
            )}
            {resuelto && (
              <button type="button" className="btn-perfil btn-perfil--ghost" disabled={enviando} onClick={() => cambiarEstado('en_revision')}>
                Reabrir
              </button>
            )}
            <button type="button" className="btn-perfil btn-perfil--ghost" onClick={onCerrar} disabled={enviando}>Cerrar</button>
            <button type="submit" className="btn-perfil" disabled={enviando || !texto.trim()}>
              {enviando ? 'Enviando...' : 'Enviar respuesta'}
            </button>
          </div>
        </form>
      </div>
    </Modal>
  );
}

export default DetalleReporteModal;
