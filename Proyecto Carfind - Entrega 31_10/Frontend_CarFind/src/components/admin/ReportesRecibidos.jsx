import React, { useState } from 'react';
import DetalleReporteModal from './DetalleReporteModal';
import useReportesRecibidos from '../../hooks/useReportesRecibidos';
import { formatearDia } from '../../utils/formatearFecha';
import { nombreEstadoReporte, nombreTipoReporte, tituloReporte } from '../../utils/reportesAdmin';
import { ESTADOS_REPORTE, TIPOS_REPORTE_RECIBIDO } from '../../types/admin';

/** Reportes que enviaron los usuarios: filtros, tabla y detalle con respuesta. */
function ReportesRecibidos() {
  const { reportes, filtros, cambiarFiltro, limpiarFiltros, loading, error, reload, responder, cambiarEstado } = useReportesRecibidos();
  const [seleccionadoId, setSeleccionadoId] = useState(null);

  const seleccionado = reportes.find((r) => r.id === seleccionadoId) ?? null;
  const pendientes = reportes.filter((r) => r.estado === 'pendiente').length;
  const hayFiltros = Object.values(filtros).some(Boolean);

  return (
    <div className="admin-bloque">
      <div className="admin-filtros">
        <label className="admin-filtro">
          Tipo
          <select className="perfil-form-input" value={filtros.tipo} onChange={(e) => cambiarFiltro('tipo', e.target.value)}>
            <option value="">Todos</option>
            {TIPOS_REPORTE_RECIBIDO.map((t) => <option key={t.id} value={t.id}>{t.nombre}</option>)}
          </select>
        </label>
        <label className="admin-filtro">
          Estado
          <select className="perfil-form-input" value={filtros.estado} onChange={(e) => cambiarFiltro('estado', e.target.value)}>
            <option value="">Todos</option>
            {ESTADOS_REPORTE.map((s) => <option key={s.id} value={s.id}>{s.nombre}</option>)}
          </select>
        </label>
        <label className="admin-filtro">
          Desde
          <input className="perfil-form-input" type="date" value={filtros.desde} max={filtros.hasta || undefined} onChange={(e) => cambiarFiltro('desde', e.target.value)} />
        </label>
        <label className="admin-filtro">
          Hasta
          <input className="perfil-form-input" type="date" value={filtros.hasta} min={filtros.desde || undefined} onChange={(e) => cambiarFiltro('hasta', e.target.value)} />
        </label>
        {hayFiltros && (
          <button type="button" className="btn-perfil btn-perfil--ghost admin-filtro-limpiar" onClick={limpiarFiltros}>Limpiar filtros</button>
        )}
      </div>

      {error ? (
        <div className="perfil-error" role="alert">
          {error} <button type="button" className="btn-support" onClick={reload}>Reintentar</button>
        </div>
      ) : loading ? (
        <p className="perfil-status">Cargando reportes...</p>
      ) : reportes.length === 0 ? (
        <p className="perfil-status">No hay reportes que coincidan con los filtros.</p>
      ) : (
        <>
          <p className="config-ayuda-texto" role="status">
            {reportes.length} {reportes.length === 1 ? 'reporte' : 'reportes'} · {pendientes} {pendientes === 1 ? 'pendiente' : 'pendientes'}
          </p>
          <div className="config-tabla-contenedor">
            <table className="config-tabla">
              <thead>
                <tr>
                  <th scope="col">Reporte</th>
                  <th scope="col">Fecha</th>
                  <th scope="col">Estado</th>
                  <th scope="col">Acción</th>
                </tr>
              </thead>
              <tbody>
                {reportes.map((r) => (
                  <tr key={r.id}>
                    <th scope="row" className="config-aviso-nombre">
                      <span className="admin-reporte-titulo">{tituloReporte(r)}</span>
                      <small className="admin-reporte-tipo">{nombreTipoReporte(r.tipo)}</small>
                    </th>
                    <td>{formatearDia(r.fecha)}</td>
                    <td><span className={`estado-badge estado-badge--${r.estado}`}>{nombreEstadoReporte(r.estado)}</span></td>
                    <td>
                      <button type="button" className="btn-perfil btn-perfil--ghost config-btn-chico" onClick={() => setSeleccionadoId(r.id)}>
                        Ver y responder
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </>
      )}

      {seleccionado && (
        <DetalleReporteModal
          reporte={seleccionado}
          onResponder={responder}
          onCambiarEstado={cambiarEstado}
          onCerrar={() => setSeleccionadoId(null)}
        />
      )}
    </div>
  );
}

export default ReportesRecibidos;
