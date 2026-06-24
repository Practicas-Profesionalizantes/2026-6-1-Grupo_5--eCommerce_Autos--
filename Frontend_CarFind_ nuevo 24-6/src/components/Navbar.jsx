import React from 'react';
import { Link } from 'react-router-dom';
import './Header.css'; // Usamos tus estilos existentes

const Navbar = () => {
  return (
    <header className="header">
      <div className="logo">
        <Link title="Volver al inicio" to="/" style={{ textDecoration: 'none', color: 'inherit', fontWeight: 'bold' }}>
         CarFind
        </Link>
      </div>

      <input 
        type="text" 
        placeholder="Buscar..." 
        className="search-bar" 
      />

      <nav className="nav-links">
        <Link to="/login">
          <button className="icon-button" title="Login">L</button>
        </Link>
        <Link to="/register">
          <button className="icon-button" title="Registro" style={{ marginLeft: '10px' }}>R</button>
        </Link>
      </nav>
    </header>
  );
};