import React from 'react';
import SoporteBox from './SoporteBox';

/**
 * @param {{ listaPrecios: object[], publicacionId?: number|string }} props
 *   publicacionId: si viene, el aviso de problema lleva a reportar ESA publicación;
 *   si no, al reporte general.
 */
function PreciosReferencia({ listaPrecios, publicacionId }) {
  return (
    <div className="reference-prices-section">
      <h2>Precios de referencia</h2>
      <div className="reference-box">
        {listaPrecios.map((ref) => (
          <div key={ref.id} className="reference-item">
            <div className="ref-thumb">
              <img src={ref.img} alt={ref.titulo} />
            </div>
            <div className="ref-details">
              <p className="ref-title">{ref.titulo}</p>
              <p className="ref-price">{ref.precio}</p>
            </div>
          </div>
        ))}
      </div>

      <SoporteBox
        texto="¿Tuviste un problema con la publicación?"
        destino={publicacionId ? `/publicacion/${publicacionId}/reportar` : '/reportes'}
      />
    </div>
  );
}

export default PreciosReferencia;