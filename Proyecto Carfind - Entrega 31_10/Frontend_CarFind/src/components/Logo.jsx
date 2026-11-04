import React from 'react';
import { Link } from 'react-router-dom';
import logoCarfind from '../assets/images/logo-carfind.png';

/**
 * Logo principal de CarFind (ícono + nombre). Lleva a la página de inicio.
 * @param {{ className?: string }} props  Clases extra para adaptarlo a cada contexto.
 */
function Logo({ className = '' }) {
  return (
    <div className={`logo ${className}`.trim()}>
      <Link to="/" className="logo-link" title="Volver al inicio">
        <img src={logoCarfind} alt="" className="logo-icono" />
        <span className="logo-texto">CarFind</span>
      </Link>
    </div>
  );
}

export default Logo;
