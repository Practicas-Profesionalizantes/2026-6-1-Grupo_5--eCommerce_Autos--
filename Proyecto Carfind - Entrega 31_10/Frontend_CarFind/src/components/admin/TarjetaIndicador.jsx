import React from 'react';

/**
 * Tarjeta con un número grande (ej: "Autos publicados: 1.248").
 * @param {{ titulo: string, valor: string, detalle?: string }} props
 */
function TarjetaIndicador({ titulo, valor, detalle }) {
  return (
    <article className="admin-indicador">
      <h3 className="admin-indicador-titulo">{titulo}</h3>
      <p className="admin-indicador-valor">{valor}</p>
      {detalle && <p className="admin-indicador-detalle">{detalle}</p>}
    </article>
  );
}

export default TarjetaIndicador;
