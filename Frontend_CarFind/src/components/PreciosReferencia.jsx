import React from 'react';

function PreciosReferencia({ listaPrecios }) {
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

      <div className="support-box">
        <small>¿Tuviste un problema con la publicación? <a href="#reportar">Avisanos</a>.</small>
        <button className="btn-support">SOPORTE/REPORTAR</button>
      </div>
    </div>
  );
}

export default PreciosReferencia;