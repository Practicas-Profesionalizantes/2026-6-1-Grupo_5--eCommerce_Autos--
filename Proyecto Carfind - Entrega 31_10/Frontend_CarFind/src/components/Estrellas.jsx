import React from 'react';
import { ESTRELLAS_MAXIMAS } from '../types/perfilPublico';

/**
 * Valoración con estrellas (0 a 5). Se redondea a la estrella entera más cercana.
 * @param {{ valor: number }} props
 */
function Estrellas({ valor }) {
  const llenas = Math.min(ESTRELLAS_MAXIMAS, Math.max(0, Math.round(valor)));

  return (
    <span className="estrellas" role="img" aria-label={`Valoración: ${valor} de ${ESTRELLAS_MAXIMAS}`}>
      {Array.from({ length: ESTRELLAS_MAXIMAS }, (_, i) => (
        <span key={i} className={i < llenas ? 'estrella estrella--llena' : 'estrella'} aria-hidden="true">
          {i < llenas ? '★' : '☆'}
        </span>
      ))}
    </span>
  );
}

export default Estrellas;
