import React, { useState } from 'react';
import useInformes from '../../hooks/useInformes';
import { formatearDia } from '../../utils/formatearFecha';
import { FORMATOS_INFORME, TIPOS_INFORME } from '../../types/admin';

/** Fecha local en formato "YYYY-MM-DD" (lo que usan los campos de fecha). */
const aFechaLocal = (fecha) => fecha.toLocaleDateString('sv-SE');

/** Genera informes (PDF o CSV) por tipo y rango de fechas, y lista los ya generados. */
function GenerarInformes() {
  const { informes, loading, error, reload, generar, generando, errorGeneracion, ver, descargar, accionandoId, errorArchivo } = useInformes();

  const [tipo, setTipo] = useState('reportes');
  const [formato, setFormato] = useState('pdf');
  // Por defecto: desde el 1° del mes actual hasta hoy.
  const [desde, setDesde] = useState(() => {
    const hoy = new Date();
    return aFechaLocal(new Date(hoy.getFullYear(), hoy.getMonth(), 1));
  });
  const [hasta, setHasta] = useState(() => aFechaLocal(new Date()));

  const fechasValidas = desde !== '' && hasta !== '' && desde <= hasta;

  const handleSubmit = (e) => {
    e.preventDefault();
    if (fechasValidas) generar({ tipo, desde, hasta, formato });
  };

  return (
    <div className="admin-bloque">
      <form className="admin-generar" onSubmit={handleSubmit}>
        <h2 className="config-bloque-titulo">Generar</h2>
        <div className="admin-generar-campos">
          <label className="admin-filtro">
            Tipo de informe
            <select className="perfil-form-input" value={tipo} onChange={(e) => setTipo(e.target.value)}>
              {TIPOS_INFORME.map((t) => <option key={t.id} value={t.id}>{t.nombre}</option>)}
            </select>
          </label>
          <label className="admin-filtro">
            Desde
            <input className="perfil-form-input" type="date" value={desde} max={hasta || undefined} onChange={(e) => setDesde(e.target.value)} required />
          </label>
          <label className="admin-filtro">
            Hasta
            <input className="perfil-form-input" type="date" value={hasta} min={desde || undefined} onChange={(e) => setHasta(e.target.value)} required />
          </label>
          <label className="admin-filtro">
            Formato
            <select className="perfil-form-input" value={formato} onChange={(e) => setFormato(e.target.value)}>
              {FORMATOS_INFORME.map((f) => <option key={f.id} value={f.id}>{f.nombre}</option>)}
            </select>
          </label>
          <button type="submit" className="btn-perfil admin-generar-btn" disabled={generando || !fechasValidas}>
            {generando ? 'Generando...' : 'GENERAR'}
          </button>
        </div>
        {errorGeneracion && <p className="perfil-error" role="alert">{errorGeneracion}</p>}
      </form>

      <section>
        <h2 className="config-bloque-titulo">Informes generados</h2>
        {errorArchivo && <p className="perfil-error" role="alert">{errorArchivo}</p>}

        {error ? (
          <div className="perfil-error" role="alert">
            {error} <button type="button" className="btn-support" onClick={reload}>Reintentar</button>
          </div>
        ) : loading ? (
          <p className="perfil-status">Cargando informes...</p>
        ) : informes.length === 0 ? (
          <p className="perfil-status">Todavía no generaste ningún informe.</p>
        ) : (
          <div className="config-tabla-contenedor">
            <table className="config-tabla">
              <thead>
                <tr>
                  <th scope="col">Nombre del informe</th>
                  <th scope="col">Fecha</th>
                  <th scope="col">Cantidad</th>
                  <th scope="col">Acción</th>
                </tr>
              </thead>
              <tbody>
                {informes.map((inf) => (
                  <tr key={inf.id}>
                    <th scope="row" className="config-aviso-nombre">
                      <span className="admin-reporte-titulo">{inf.nombre}</span>
                      <small className="admin-reporte-tipo">{inf.formato.toUpperCase()}</small>
                    </th>
                    <td>{formatearDia(inf.fecha)}</td>
                    <td>{inf.cantidad.toLocaleString('es-AR')}</td>
                    <td>
                      <div className="admin-acciones-fila">
                        <button type="button" className="btn-perfil btn-perfil--ghost config-btn-chico" disabled={accionandoId === inf.id} onClick={() => ver(inf.id)}>
                          Ver
                        </button>
                        <button type="button" className="btn-perfil btn-perfil--ghost config-btn-chico" disabled={accionandoId === inf.id} onClick={() => descargar(inf.id)}>
                          Descargar
                        </button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </section>
    </div>
  );
}

export default GenerarInformes;
