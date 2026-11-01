import React, { useState } from 'react';
import TarjetaIndicador from '../../components/admin/TarjetaIndicador';
import BarrasHorizontales from '../../components/admin/graficos/BarrasHorizontales';
import BarrasVerticales from '../../components/admin/graficos/BarrasVerticales';
import Donut from '../../components/admin/graficos/Donut';
import LineaEvolucion from '../../components/admin/graficos/LineaEvolucion';
import useEstadisticasAdmin from '../../hooks/useEstadisticasAdmin';
import formatearPrecio from '../../utils/formatearPrecio';
import { DIMENSIONES_DISTRIBUCION, OPCIONES_MESES_EVOLUCION } from '../../types/admin';

/**
 * Estadísticas de la plataforma. CarFind no permite la compra directa, por eso no hay
 * ventas: se muestran publicaciones, usuarios, wishlist y conversaciones.
 */
function EstadisticasAdmin() {
  const [meses, setMeses] = useState(OPCIONES_MESES_EVOLUCION[0]);
  const [dimension, setDimension] = useState('marca');
  const { datos, loading, actualizando, error, reload } = useEstadisticasAdmin(meses);

  if (error && !datos) {
    return (
      <div className="perfil-error" role="alert">
        {error} <button type="button" className="btn-support" onClick={reload}>Reintentar</button>
      </div>
    );
  }
  if (loading || !datos) return <p className="perfil-status">Cargando estadísticas...</p>;

  const { resumen, masGuardados, evolucion, distribuciones, porUbicacion } = datos;
  const dim = DIMENSIONES_DISTRIBUCION.find((d) => d.id === dimension);

  return (
    <div className="admin-pagina" aria-busy={actualizando}>
      <h1 className="admin-titulo">Estadísticas</h1>
      {error && <p className="perfil-error" role="alert">{error}</p>}

      <section className="admin-indicadores" aria-label="Indicadores generales">
        <TarjetaIndicador titulo="Autos publicados" valor={resumen.publicaciones.toLocaleString('es-AR')} />
        <TarjetaIndicador titulo="Contactos iniciados" valor={resumen.conversaciones.toLocaleString('es-AR')} detalle="Chats abiertos desde una publicación" />
        <TarjetaIndicador titulo="Usuarios registrados" valor={resumen.usuarios.toLocaleString('es-AR')} />
        <TarjetaIndicador titulo="Precio promedio" valor={formatearPrecio(resumen.precioPromedio)} />
      </section>

      <div className="admin-grilla-graficos">
        <section className="admin-tarjeta-grafico">
          <h2 className="admin-grafico-titulo">Autos más guardados</h2>
          <p className="config-ayuda-texto">Publicaciones que más usuarios guardaron en su lista de deseos.</p>
          <BarrasHorizontales
            descripcion="Autos más guardados en listas de deseos"
            items={masGuardados.map((m) => ({ etiqueta: m.titulo, valor: m.cantidad }))}
          />
        </section>

        <section className="admin-tarjeta-grafico">
          <div className="admin-tarjeta-cabecera">
            <h2 className="admin-grafico-titulo">Evolución de publicaciones y usuarios</h2>
            <label className="admin-selector">
              Período
              <select className="perfil-form-input" value={meses} onChange={(e) => setMeses(Number(e.target.value))}>
                {OPCIONES_MESES_EVOLUCION.map((m) => <option key={m} value={m}>Últimos {m} meses</option>)}
              </select>
            </label>
          </div>
          <LineaEvolucion
            descripcion="Evolución mensual de publicaciones nuevas y usuarios nuevos"
            etiquetas={evolucion.etiquetas}
            series={[
              { nombre: 'Publicaciones nuevas', valores: evolucion.publicaciones },
              { nombre: 'Usuarios nuevos', valores: evolucion.usuarios },
            ]}
          />
        </section>

        <section className="admin-tarjeta-grafico">
          <div className="admin-tarjeta-cabecera">
            <h2 className="admin-grafico-titulo">{dim.titulo}</h2>
            <label className="admin-selector">
              Ver por
              <select className="perfil-form-input" value={dimension} onChange={(e) => setDimension(e.target.value)}>
                {DIMENSIONES_DISTRIBUCION.map((d) => <option key={d.id} value={d.id}>{d.nombre}</option>)}
              </select>
            </label>
          </div>
          <Donut descripcion={dim.titulo} items={distribuciones[dimension]} />
        </section>

        <section className="admin-tarjeta-grafico">
          <h2 className="admin-grafico-titulo">Autos por ubicación</h2>
          <BarrasVerticales
            descripcion="Cantidad de autos publicados por provincia"
            items={porUbicacion.map((u) => ({ etiqueta: u.nombre, valor: u.cantidad }))}
          />
        </section>
      </div>
    </div>
  );
}

export default EstadisticasAdmin;
