import "../assets/styles/App.css";
import { Link } from 'react-router-dom';
import { useState } from "react";
import { useNavigate } from "react-router-dom";

const Registro = () => {
  const[email, setEmail] = useState("");
  const[password, setPassword] = useState("");
  const[dni, setDni] = useState("");
  const[telefono, setTelefono] = useState("");
  const[nombre, setNombre] = useState("");
  const navigate = useNavigate();

  return (
    <div className="app-wrapper">
      <header className="header">
        <div className="logo">
          <Link title="Volver al inicio" to="/" > 
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

          <form className="auth-form">
            <input 
            className="search-bar"
            type="dni"
            placeholder="DNI"
            value={dni}
            onChange={(e) => setDni(e.target.value)} 
            />
            <input
             className="search-bar"
              type="email"
              placeholder="Correo Electronico"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              />
            <input
             className="search-bar"
             type="nombre" 
             placeholder="Nombre y Apellido"
             value={nombre}
             onChange={(e) => setNombre(e.tagret.value)}
             />
             <input 
             className="search-bar"
             type="telefono" 
             placeholder="Número telefonico"
             value={telefono}
             onChange={(e) => setTelefono(e.target.value)}
             />
             <input 
             className="search-bar"
             type="password"
             placeholder="Contraseña"
             value={password}
             onChange={(e) => setPassword(e.target.value)}
             />
             <button type="submit" className="navButton" onClick={() => navigate("/login")}>Completar Registro</button>
             
          </form>
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