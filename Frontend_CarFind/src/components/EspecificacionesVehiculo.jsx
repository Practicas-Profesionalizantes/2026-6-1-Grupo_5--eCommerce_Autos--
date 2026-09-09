import React from 'react';

function EspecificacionesVehiculo({ caracteristicas }) {
  return (
    <div className="specs-card">
      <h3>Características del producto</h3>
      <div className="specs-grid">
        <div>
          <p><strong>Marca / Modelo:</strong> {caracteristicas.marca} - {caracteristicas.modelo}</p>
          <p><strong>Año:</strong> {caracteristicas.anio}</p>
          <p><strong>Motor:</strong> {caracteristicas.motor}</p>
          <p><strong>Transmisión:</strong> {caracteristicas.transmision}</p>
          <p><strong>Tipo de combustible:</strong> {caracteristicas.combustible}</p>
          <p><strong>Papeles al día:</strong> {caracteristicas.papelesAlDia}</p>
        </div>
        <div>
          <p><strong>Kilometraje:</strong> {caracteristicas.kilometraje.toLocaleString()} km</p>
          <p><strong>Color:</strong> {caracteristicas.color}</p>
          <h4 style={{ margin: "10px 0 5px 0" }}>Otras Especificaciones:</h4>
          <p>VTV: {caracteristicas.vtv ? "Sí" : "No"}</p>
          <p>Grabado de autopartes: {caracteristicas.grabado ? "Sí" : "No"}</p>
        </div>
      </div>
    </div>
  );
}

export default EspecificacionesVehiculo;