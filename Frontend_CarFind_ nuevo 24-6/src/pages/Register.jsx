import "../assets/styles/App.css";
import { Link } from 'react-router-dom';
import { useState } from "react";
import { useNavigate } from "react-router-dom";

const Registro = () => {
  const [form, setForm] = useState({ nombre: "", dni: "", email: "", password: "" });
  const navigate = useNavigate();

  return (
    <div className="app-wrapper">
      <header className="header">
        <div className="logo">
          <Link title="Volver al inicio" to="/" style={{ textDecoration: 'none', color: 'inherit', fontWeight: 'bold' }}> 
          CarFind
          </Link>
        </div>
        <button className="navButton" onClick={() => navigate("/login")}>Iniciar Sesión</button>
      </header>

      <div className="main-container">
        <div className="side-panel">
          <p>imagen de autitos</p>
        </div>

        <div className="form-panel">
          <h2>TU VIAJE EMPIEZA ACA</h2>
          <p>CON NOSOTROS</p>

          <div className="register-table">
            <div className="row"><span>Nombre y apellido:</span><input className="search-bar" /></div>
            <div className="row"><span>Número:</span><input className="search-bar" /></div>
            <div className="row"><span>Gmail:</span><input className="search-bar" /></div>
            <div className="row"><span>Contraseña:</span><input className="search-bar" type="password" /></div>
          </div>

          <button className="navButton" style={{marginTop: '20px'}}>Ingresar</button>
          <button className="invitado-btn" onClick={() => navigate("/")}>Ingresar como invitado</button>
        </div>

        <div className="side-panel">
          <p>imagen del rayo mcqueen</p>
          <button className="clear-filters-btn">Obtener ayuda ?</button>
        </div>
      </div>
    </div>
  );
};

export default Registro;