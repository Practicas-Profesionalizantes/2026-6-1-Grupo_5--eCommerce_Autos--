import React from 'react';
import { COLORES_GRAFICO, formatearNumero } from './colores';

const ANCHO = 440;
const ALTO = 230;
const MARGEN = { izquierda: 40, derecha: 14, arriba: 14, abajo: 30 };
const DIVISIONES = 4;

/** Menor tope "redondo" (1, 2, 2.5, 5 o 10 por una potencia de 10) que deja aire sobre el valor máximo. */
function topeRedondo(maximo) {
  const bruto = Math.max(maximo * 1.1, 1);
  const magnitud = 10 ** Math.floor(Math.log10(bruto));
  const multiplo = [1, 2, 2.5, 5, 10].find((m) => m * magnitud >= bruto);
  return multiplo * magnitud;
}

/**
 * Gráfico de líneas con área bajo la primera serie (ej: publicaciones y usuarios nuevos por mes).
 * @param {{ etiquetas: string[], series: { nombre: string, valores: number[] }[], descripcion: string }} props
 */
function LineaEvolucion({ etiquetas, series, descripcion }) {
  const tope = topeRedondo(Math.max(...series.flatMap((s) => s.valores), 1));
  const ancho = ANCHO - MARGEN.izquierda - MARGEN.derecha;
  const alto = ALTO - MARGEN.arriba - MARGEN.abajo;

  const x = (i) => MARGEN.izquierda + (etiquetas.length === 1 ? ancho / 2 : (i * ancho) / (etiquetas.length - 1));
  const y = (valor) => MARGEN.arriba + (1 - valor / tope) * alto;
  const camino = (valores) => valores.map((v, i) => `${i ? 'L' : 'M'}${x(i).toFixed(1)} ${y(v).toFixed(1)}`).join(' ');

  const lineasGuia = Array.from({ length: DIVISIONES + 1 }, (_, i) => (tope / DIVISIONES) * i);
  const saltoEtiquetas = etiquetas.length > 8 ? 2 : 1;

  const resumen = series.map((s) => `${s.nombre}: ${s.valores.map(formatearNumero).join(', ')}`).join('. ');

  return (
    <div className="grafico-linea">
      <svg viewBox={`0 0 ${ANCHO} ${ALTO}`} role="img" aria-label={`${descripcion}. Meses: ${etiquetas.join(', ')}. ${resumen}`} className="grafico-linea-svg">
        {lineasGuia.map((valor) => (
          <g key={valor}>
            <line x1={MARGEN.izquierda} x2={ANCHO - MARGEN.derecha} y1={y(valor)} y2={y(valor)} className="grafico-guia" />
            <text x={MARGEN.izquierda - 6} y={y(valor) + 4} textAnchor="end" className="grafico-texto">{formatearNumero(Math.round(valor))}</text>
          </g>
        ))}

        {/* Área bajo la primera serie */}
        <path
          d={`${camino(series[0].valores)} L${x(etiquetas.length - 1).toFixed(1)} ${y(0)} L${x(0).toFixed(1)} ${y(0)} Z`}
          fill={COLORES_GRAFICO[0]}
          opacity="0.15"
        />

        {series.map((serie, s) => (
          <g key={serie.nombre}>
            <path d={camino(serie.valores)} fill="none" stroke={COLORES_GRAFICO[s % COLORES_GRAFICO.length]} strokeWidth="2.5" strokeLinejoin="round" />
            {serie.valores.map((v, i) => (
              <circle key={i} cx={x(i)} cy={y(v)} r="3.5" fill={COLORES_GRAFICO[s % COLORES_GRAFICO.length]} />
            ))}
          </g>
        ))}

        {etiquetas.map((etiqueta, i) =>
          i % saltoEtiquetas === 0 ? (
            <text key={`${etiqueta}-${i}`} x={x(i)} y={ALTO - 8} textAnchor="middle" className="grafico-texto">{etiqueta}</text>
          ) : null
        )}
      </svg>

      <ul className="grafico-leyenda grafico-leyenda--fila">
        {series.map((serie, s) => (
          <li key={serie.nombre} className="grafico-leyenda-item">
            <span className="grafico-leyenda-color" style={{ background: COLORES_GRAFICO[s % COLORES_GRAFICO.length] }} aria-hidden="true" />
            {serie.nombre}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default LineaEvolucion;
