import React, { useState } from 'react';
import { Link, useLocation, useNavigate, useParams } from 'react-router-dom';
import PublicacionPerfil from '../components/PublicacionPerfil';
import CampoDescripcion from '../components/reportes/CampoDescripcion';
import ReporteEnviado from '../components/reportes/ReporteEnviado';
import SoporteBox from '../components/SoporteBox';
import useReportePublicacion from '../hooks/useReportePublicacion';
import { MOTIVOS_REPORTE_PUBLICACION, REPORTE_PUBLICACION_MAX_CARACTERES } from '../types/reportes';
import '../assets/styles/App.css';

function ReportePublicacionContenido({ id }) {
  const navigate = useNavigate();
  const location = useLocation();
  const { publicacion, loading, error, reload, enviar, enviando, enviado, errorEnvio } = useReportePublicacion(id);
  const [motivo, setMotivo] = useState('');
  const [descripcion, setDescripcion] = useState('');

  // "Otro motivo" obliga a explicar; con los demás motivos el texto es opcional.
  const puedeEnviar = motivo !== '' && (motivo !== 'otro' || descripcion.trim() !== '');

  // Si entró directo a esta URL no hay "atrás" dentro de la app: se lo lleva a la publicación.
  const cancelar = () => (location.key !== 'default' ? navigate(-1) : navigate(`/publicacion/${id}`));

  const handleSubmit = (e) => {
    e.preventDefault();
    if (puedeEnviar) enviar({ motivo, descripcion: descripcion.trim() });
  };

  return (
    <main className="reporte-container reporte-container--ancho">
      <div className="reporte-barra">
        <Link to="/" className="reporte-volver">← Inicio</Link>
        <Link to="/perfil" className="btn-perfil btn-perfil--ghost">Perfil</Link>
      </div>

      <h1 className="reporte-titulo">REPORTAR PUBLICACIÓN</h1>
      <p className="reporte-aviso">Ayudanos a entender qué sucede, tu reporte nos ayuda a mejorar</p>

      {enviado ? (
        <ReporteEnviado>
          <Link to={`/publicacion/${id}`} className="btn-perfil">Volver a la publicación</Link>
          <Link to="/" className="btn-perfil btn-perfil--ghost">Ir al inicio</Link>
        </ReporteEnviado>
      ) : (
        <form className="reporte-form" onSubmit={handleSubmit}>
          <section className="reporte-campo">
            <h2 className="reporte-etiqueta">Publicación que estás reportando</h2>
            {loading ? (
              <p className="perfil-status">Cargando publicación...</p>
            ) : error || !publicacion ? (
              <div className="perfil-error" role="alert">
                {error} <button type="button" className="btn-support" onClick={reload}>Reintentar</button>
              </div>
            ) : (
              <PublicacionPerfil publicacion={publicacion} />
            )}
          </section>

          <fieldset className="reporte-campo reporte-motivos">
            <legend className="reporte-etiqueta">¿Cuál es el motivo?</legend>
            <div className="reporte-motivos-grilla">
              {MOTIVOS_REPORTE_PUBLICACION.map((m) => (
                <label key={m.id} className={`reporte-motivo${motivo === m.id ? ' reporte-motivo--elegido' : ''}`}>
                  <input type="radio" name="motivo" value={m.id} checked={motivo === m.id} onChange={() => setMotivo(m.id)} />
                  {m.nombre}
                </label>
              ))}
            </div>
          </fieldset>

          <CampoDescripcion
            id="reporte-publicacion-descripcion"
            etiqueta={motivo === 'otro' ? 'Escribí tu problema, sugerencia o duda (obligatorio)' : 'Escribí tu problema, sugerencia o duda'}
            valor={descripcion}
            onChange={setDescripcion}
            max={REPORTE_PUBLICACION_MAX_CARACTERES}
            filas={7}
          />

          {errorEnvio && <p className="perfil-error" role="alert">{errorEnvio}</p>}

          <div className="reporte-acciones">
            <button type="button" className="btn-perfil btn-perfil--ghost" onClick={cancelar} disabled={enviando}>Cancelar</button>
            <button type="submit" className="btn-perfil" disabled={enviando || !puedeEnviar || !publicacion}>
              {enviando ? 'Enviando...' : 'Reportar'}
            </button>
          </div>
        </form>
      )}

      <SoporteBox />
    </main>
  );
}

/** El `key` por id hace que el formulario arranque limpio al reportar otra publicación. */
function ReportePublicacion() {
  const { id } = useParams();
  return <ReportePublicacionContenido key={id} id={id} />;
}

export default ReportePublicacion;
