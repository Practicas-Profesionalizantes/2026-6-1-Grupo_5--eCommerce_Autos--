import React from 'react';

/**
 * Confirmación que reemplaza al formulario cuando el reporte se envió bien.
 * @param {{ children?: React.ReactNode }} props  Botones o enlaces de salida
 */
function ReporteEnviado({ children }) {
  return (
    <div className="reporte-enviado" role="status">
      <h2 className="reporte-enviado-titulo">¡Gracias por tu reporte!</h2>
      <p>Lo recibimos y lo vamos a revisar. Tu aporte nos ayuda a mejorar CarFind.</p>
      <div className="reporte-enviado-acciones">{children}</div>
    </div>
  );
}

export default ReporteEnviado;
