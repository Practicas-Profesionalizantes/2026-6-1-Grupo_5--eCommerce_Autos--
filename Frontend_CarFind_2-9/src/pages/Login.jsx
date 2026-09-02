import "../assets/styles/App.css";
import { Link } from 'react-router-dom';
import { useState } from "react";
import { useNavigate } from "react-router-dom";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  return (
    <div className="app-wrapper">
      <header className="header">
        <div className="logo">
          <Link title="Volver al inicio" to="/" style={{ textDecoration: 'none', color: 'inherit', fontWeight: 'bold' }}> 
          CarFind
          </Link>
        </div>
        <div>
          <button className="navButton" onClick={()=> navigate("/Home")}>Comprar Vehiculos</button>
          <button className="navButton" onClick={() => navigate("/register")}>Registrarse</button>
        </div>
        
      </header>

      <div className="main-container"> {/* Layout de tres columnas */}
        <div className="side-panel">
          <p>imagen</p>
          <button className="clear-filters-btn">EXPLORA OFERTAS</button>
        </div>

        <div className="form-panel"> {/* Centro: Formulario */}
          <h2>TU VIAJE EMPIEZA ACA</h2>
          <p>CON NOSOTROS</p>
          
          <form className="auth-form">
            <input 
              className="search-bar" /* Clase para bordes redondeados y lila [8] */
              type="email" 
              placeholder="Correo Electronico" 
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
            <input 
              className="search-bar"
              type="password" 
              placeholder="contraseña"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
            <button type="submit" className="navButton">ingresar</button>
          </form>

          <div className="extra-options">
            <button className="search-bar">opcion de logueo</button>
            <button className="navButton" onClick={() => navigate("/register")}>REGISTRARSE</button>
          </div>
        </div>

        <div className="side-panel">
          {/* Panel para la imagen derecha del wireframe [3] */}
          <p>imagen</p>
          <button className="clear-filters-btn">SABER MAS</button>
        </div>
      </div>
    </div>
  );
};

export default Login;