import React from 'react';
import { formatearNumero } from './colores';

/**
 * Columnas verticales (ej: autos por ubicación).
 * @param {{ items: { etiqueta: string, valor: number }[], descripcion: string }} props
 */
function BarrasVerticales({ items, descripcion }) {
  const maximo = Math.max(...items.map((i) => i.valor), 1);

  return (
    <ul className="grafico-barras-v" aria-label={descripcion}>
      {items.map((item, posicion) => (
        <li key={`${posicion}-${item.etiqueta}`} className="grafico-barras-v-columna">
          <span className="grafico-barras-v-valor">{formatearNumero(item.valor)}</span>
          <span className="grafico-barras-v-pista" aria-hidden="true">
            <span className="grafico-barras-v-barra" style={{ height: `${(item.valor / maximo) * 100}%` }} />
          </span>
          <span className="grafico-barras-v-etiqueta">{item.etiqueta}</span>
        </li>
      ))}
    </ul>
  );
}

export default BarrasVerticales;
