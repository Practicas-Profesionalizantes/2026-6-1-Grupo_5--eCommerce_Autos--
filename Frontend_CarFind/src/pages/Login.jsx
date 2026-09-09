import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import AuthLayout from "../components/AuthLayout";
import AutoI from "../assets/images/ImgIzquierda.webp";
import AutoD from "../assets/images/ImgIzquierda.webp";
import "../assets/styles/App.css";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Login enviando:", { email, password });
  };

  return (
    <AuthLayout
      panelIzquierdo={
        <>
          <img src={AutoI} alt="Explora ofertas" />
          <button className="clear-filters-btn">EXPLORA OFERTAS</button>
        </>
      }
      panelDerecho={
        <>
          <img src={AutoD} alt="Saber más" />
          <button className="clear-filters-btn">SABER MÁS</button>
        </>
      }
    >
      <h2>TU VIAJE EMPIEZA ACÁ</h2>
      <p>CON NOSOTROS</p>

      <form className="auth-form" onSubmit={handleSubmit}>
        <input 
          className="search-bar"
          type="email" 
          placeholder="Correo Electrónico" 
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />
        <input 
          className="search-bar"
          type="password" 
          placeholder="Contraseña"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />
        <button type="submit" className="navButton">Ingresar</button>
      </form>

      <div className="extra-options">
        <button type="button" className="search-bar">Opción de logueo</button>
        <button type="button" className="navButton" onClick={() => navigate("/register")}>
          REGISTRARSE
        </button>
      </div>
    </AuthLayout>
  );
};

export default Login;