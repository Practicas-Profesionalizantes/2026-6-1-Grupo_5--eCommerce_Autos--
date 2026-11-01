import React from 'react';
import { formatearFechaEnvio } from '../../utils/formatearFecha';

const TEXTO_ESTADO = { enviado: 'Enviado', entregado: 'Entregado', leido: 'Leído' };

/**
 * Un mensaje. Los propios van a la derecha y muestran las tildes de estado.
 * @param {{ mensaje: import('../../types/mensajeria').Mensaje }} props
 */
function BurbujaMensaje({ mensaje }) {
  const { texto, imagenUrl, fecha, propio, estado } = mensaje;

  return (
    <div className={`burbuja ${propio ? 'burbuja--propia' : 'burbuja--ajena'}`}>
      {imagenUrl && <img className="burbuja-imagen" src={imagenUrl} alt="Foto enviada" />}
      {texto && <p className="burbuja-texto">{texto}</p>}
      <span className="burbuja-meta">
        <time dateTime={fecha}>{formatearFechaEnvio(fecha)}</time>
        {propio && (
          <span className={`burbuja-tildes${estado === 'leido' ? ' burbuja-tildes--leido' : ''}`} role="img" aria-label={TEXTO_ESTADO[estado] ?? ''}>
            {estado === 'enviado' ? '✓' : '✓✓'}
          </span>
        )}
      </span>
    </div>
  );
}

export default BurbujaMensaje;
