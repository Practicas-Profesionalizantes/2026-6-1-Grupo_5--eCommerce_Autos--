import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import AuthLayout from "../components/AuthLayout";
import AutoI from "../assets/images/ImgIzquierda.webp";
import AutoD from "../assets/images/ImgIzquierda.webp";
import useAuth from "../hooks/useAuth";
import "../assets/styles/App.css";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [enviando, setEnviando] = useState(false);
  const [error, setError] = useState(null);
  const navigate = useNavigate();
  const location = useLocation();
  const { login } = useAuth();

  // Si se llegó al login desde una pantalla que pide sesión, se vuelve a ella.
  const destino = location.state?.desde ?? "/";

  const handleSubmit = async (e) => {
    e.preventDefault();
    setEnviando(true);
    setError(null);
    try {
      await login({ email, password });
      navigate(destino, { replace: true });
    } catch (err) {
      setError(err.message || "No pudimos iniciar sesión. Intentá de nuevo.");
      setEnviando(false);
    }
  };

  return (
    <AuthLayout
      panelIzquierdo={
        <>
          <img src={AutoI} alt="Explora ofertas" />
          <button className="clear-filters-btn" onClick={() => navigate("/")}>Explorá ofertas</button>
        </>
      }
      panelDerecho={
        <>
          <img src={AutoD} alt="Saber más" />
          <button className="clear-filters-btn" onClick={() => navigate("/terminos")}>Saber más</button>
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
        {error && <p className="perfil-error" role="alert">{error}</p>}
        <button type="submit" className="navButton" disabled={enviando}>
          {enviando ? "Ingresando..." : "Ingresar"}
        </button>
      </form>

      <div className="extra-options">
        <button type="button" className="navButton navButton-fantasma">Opción de logueo</button>
        <button type="button" className="navButton navButton-secundario" onClick={() => navigate("/register")}>
          Registrarse
        </button>
      </div>
    </AuthLayout>
  );
};

export default Login;