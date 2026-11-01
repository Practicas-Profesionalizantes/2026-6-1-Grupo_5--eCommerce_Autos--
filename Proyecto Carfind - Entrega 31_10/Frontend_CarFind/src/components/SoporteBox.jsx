import React from 'react';
import { useNavigate } from 'react-router-dom';

/**
 * Aviso + botón de Soporte/Reportar (mismo markup y estilos que en el resto de las páginas).
 * Por defecto lleva al reporte general; `destino` permite apuntar a otro reporte
 * (por ejemplo, el de una publicación puntual).
 * @param {{ texto?: string, destino?: string }} props
 */
function SoporteBox({ texto = '¿Tuviste un problema?', destino = '/reportes' }) {
  const navigate = useNavigate();
  return (
    <div className="support-box">
      <small>{texto} <a href={destino} onClick={(e) => { e.preventDefault(); navigate(destino); }}>Avisanos</a>.</small>
      <button type="button" className="btn-support" onClick={() => navigate(destino)}>
        SOPORTE/REPORTAR
      </button>
    </div>
  );
}

export default SoporteBox;
