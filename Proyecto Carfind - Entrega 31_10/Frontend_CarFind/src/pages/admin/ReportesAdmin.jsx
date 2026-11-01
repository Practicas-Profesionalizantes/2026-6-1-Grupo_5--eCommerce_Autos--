import React, { useState } from 'react';
import ReportesRecibidos from '../../components/admin/ReportesRecibidos';
import GenerarInformes from '../../components/admin/GenerarInformes';

const PESTANAS = [
  { id: 'recibidos', nombre: 'Reportes recibidos' },
  { id: 'informes', nombre: 'Generar informes' },
];

/**
 * Reportes del panel de administración, en dos pestañas:
 *  - Reportes recibidos: consultar y responder lo que enviaron los usuarios.
 *  - Generar informes: exportar datos en PDF o CSV por rango de fechas.
 */
function ReportesAdmin() {
  const [pestana, setPestana] = useState('recibidos');

  return (
    <div className="admin-pagina">
      <h1 className="admin-titulo">Reportes</h1>

      <div className="admin-pestanas" role="tablist" aria-label="Secciones de reportes">
        {PESTANAS.map((p) => (
          <button
            key={p.id}
            type="button"
            role="tab"
            id={`pestana-${p.id}`}
            aria-selected={pestana === p.id}
            aria-controls={`panel-${p.id}`}
            className={`admin-pestana${pestana === p.id ? ' admin-pestana--activa' : ''}`}
            onClick={() => setPestana(p.id)}
          >
            {p.nombre}
          </button>
        ))}
      </div>

      <div role="tabpanel" id={`panel-${pestana}`} aria-labelledby={`pestana-${pestana}`}>
        {pestana === 'recibidos' ? <ReportesRecibidos /> : <GenerarInformes />}
      </div>
    </div>
  );
}

export default ReportesAdmin;
