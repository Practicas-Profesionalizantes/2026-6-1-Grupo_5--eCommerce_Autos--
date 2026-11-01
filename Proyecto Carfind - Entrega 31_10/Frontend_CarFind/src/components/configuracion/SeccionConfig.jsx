import React from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import AyudaConfig from './AyudaConfig';

/**
 * Marco común de cada vista de Configuración: flecha para volver, título,
 * contenido y "Obtener ayuda ?" abajo a la derecha.
 *
 * @param {{
 *   titulo: string,
 *   ayuda: { titulo: string, texto: string }[],
 *   children: React.ReactNode,
 * }} props
 */
function SeccionConfig({ titulo, ayuda, children }) {
  const navigate = useNavigate();
  const location = useLocation();

  // Si el usuario entró directo a esta URL no hay "atrás" dentro de la app: se lo lleva al perfil.
  const volver = () => (location.key !== 'default' ? navigate(-1) : navigate('/perfil'));

  return (
    <div className="config-seccion">
      <header className="config-seccion-cabecera">
        <button type="button" className="config-volver" onClick={volver} aria-label="Volver">
          ←
        </button>
        <h1 className="config-seccion-titulo">{titulo}</h1>
      </header>

      <div className="config-seccion-cuerpo">{children}</div>

      <footer className="config-seccion-pie">
        <AyudaConfig titulo={titulo} items={ayuda} />
      </footer>
    </div>
  );
}

export default SeccionConfig;
