import React from 'react';
import { COLORES_GRAFICO, formatearNumero } from './colores';

const RADIO = 70;
const CIRCUNFERENCIA = 2 * Math.PI * RADIO;

/**
 * Gráfico circular con hueco al centro y leyenda con porcentajes.
 * @param {{ items: { nombre: string, cantidad: number }[], descripcion: string, icono?: string }} props
 */
function Donut({ items, descripcion, icono = '🚗' }) {
  const total = items.reduce((suma, i) => suma + i.cantidad, 0);
  const porcentaje = (cantidad) => (total ? Math.round((cantidad / total) * 100) : 0);

  let acumulado = 0;
  const segmentos = items.map((item, i) => {
    const largo = total ? (item.cantidad / total) * CIRCUNFERENCIA : 0;
    const segmento = (
      <circle
        key={item.nombre}
        cx="100"
        cy="100"
        r={RADIO}
        fill="none"
        stroke={COLORES_GRAFICO[i % COLORES_GRAFICO.length]}
        strokeWidth="32"
        strokeDasharray={`${largo} ${CIRCUNFERENCIA - largo}`}
        strokeDashoffset={-acumulado}
      />
    );
    acumulado += largo;
    return segmento;
  });

  const resumen = items.map((i) => `${i.nombre} ${porcentaje(i.cantidad)}%`).join(', ');

  return (
    <div className="grafico-donut">
      <svg viewBox="0 0 200 200" role="img" aria-label={`${descripcion}: ${resumen}`} className="grafico-donut-svg">
        <g transform="rotate(-90 100 100)">{segmentos}</g>
        <text x="100" y="112" textAnchor="middle" fontSize="34" aria-hidden="true">{icono}</text>
      </svg>

      <ul className="grafico-leyenda">
        {items.map((item, i) => (
          <li key={item.nombre} className="grafico-leyenda-item">
            <span className="grafico-leyenda-color" style={{ background: COLORES_GRAFICO[i % COLORES_GRAFICO.length] }} aria-hidden="true" />
            <span className="grafico-leyenda-nombre">{item.nombre}</span>
            <strong>{porcentaje(item.cantidad)}%</strong>
            <span className="grafico-leyenda-cantidad">({formatearNumero(item.cantidad)})</span>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default Donut;
