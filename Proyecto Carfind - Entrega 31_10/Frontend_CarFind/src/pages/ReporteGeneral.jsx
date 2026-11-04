import React, { useState } from 'react';
import { Link, useLocation } from 'react-router-dom';
import CampoDescripcion from '../components/reportes/CampoDescripcion';
import ReporteEnviado from '../components/reportes/ReporteEnviado';
import useReporteGeneral from '../hooks/useReporteGeneral';
import { REPORTE_GENERAL_MAX_CARACTERES } from '../types/reportes';
import '../assets/styles/App.css';

/**
 * Reportes generales hacia la página: dudas, problemas y sugerencias.
 * Si se llega desde el perfil o el chat de otro usuario ("Reportar"), la ruta trae
 * `state.usuarioReportado` ({ id, nombre }) y el reporte se envía asociado a ese usuario.
 */
function ReporteGeneral() {
  const location = useLocation();
  const usuarioReportado = location.state?.usuarioReportado ?? null;
  const { enviar, enviando, enviado, error, reiniciar } = useReporteGeneral();
  const [mensaje, setMensaje] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    const ok = await enviar({
      mensaje: mensaje.trim(),
      ...(usuarioReportado ? { usuarioReportadoId: usuarioReportado.id } : {}),
    });
    if (ok) setMensaje('');
  };

  return (
    <main className="reporte-container">
      <h1 className="reporte-titulo">REPORTES</h1>
      <p className="reporte-aviso">Ayudanos a entender qué sucede, tu reporte nos ayuda a mejorar</p>

      {usuarioReportado && (
        <p className="reporte-contexto">
          Estás reportando al usuario <strong>{usuarioReportado.nombre}</strong>. Contanos qué pasó.
        </p>
      )}

      {enviado ? (
        <ReporteEnviado>
          <Link to="/" className="btn-perfil">Volver al inicio</Link>
          <button type="button" className="btn-perfil btn-perfil--ghost" onClick={reiniciar}>Enviar otro reporte</button>
        </ReporteEnviado>
      ) : (
        <form className="reporte-form" onSubmit={handleSubmit}>
          <CampoDescripcion
            id="reporte-general-mensaje"
            etiqueta="Escribí tu problema, sugerencia o duda"
            valor={mensaje}
            onChange={setMensaje}
            max={REPORTE_GENERAL_MAX_CARACTERES}
            filas={12}
            requerido
          />

          {error && <p className="perfil-error" role="alert">{error}</p>}

          <div className="reporte-acciones">
            <button type="submit" className="btn-perfil" disabled={enviando || mensaje.trim() === ''}>
              {enviando ? 'Enviando...' : 'Reportar'}
            </button>
          </div>
        </form>
      )}
    </main>
  );
}

export default ReporteGeneral;
