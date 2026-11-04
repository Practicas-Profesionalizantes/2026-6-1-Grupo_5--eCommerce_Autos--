import React from 'react';
import { formatearNumero } from './colores';

/**
 * Lista con barras horizontales (ej: autos más guardados).
 * @param {{ items: { etiqueta: string, valor: number }[], descripcion: string }} props
 *   descripcion: qué muestra el gráfico (para lectores de pantalla)
 */
function BarrasHorizontales({ items, descripcion }) {
  const maximo = Math.max(...items.map((i) => i.valor), 1);

  return (
    <ul className="grafico-barras-h" aria-label={descripcion}>
      {items.map((item, posicion) => (
        <li key={`${posicion}-${item.etiqueta}`} className="grafico-barras-h-fila">
          <span className="grafico-barras-h-etiqueta">{item.etiqueta}</span>
          <span className="grafico-barras-h-pista" aria-hidden="true">
            <span className="grafico-barras-h-barra" style={{ width: `${(item.valor / maximo) * 100}%` }} />
          </span>
          <span className="grafico-barras-h-valor">{formatearNumero(item.valor)}</span>
        </li>
      ))}
    </ul>
  );
}

export default BarrasHorizontales;
