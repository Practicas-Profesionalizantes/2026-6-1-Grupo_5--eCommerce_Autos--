import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import AuthLayout from "../components/AuthLayout";
import autoImg from "../assets/images/ImgIzquierda.webp";
import useAuth from "../hooks/useAuth";
import "../assets/styles/App.css";

const Registro = () => {
  const [formData, setFormData] = useState({
    dni: "",
    email: "",
    nombre: "",
    telefono: "",
    password: "",
  });

  const [enviando, setEnviando] = useState(false);
  const [error, setError] = useState(null);
  const navigate = useNavigate();
  const { registrar } = useAuth();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setEnviando(true);
    setError(null);
    try {
      await registrar(formData); // el backend crea la cuenta y deja la sesión iniciada
      navigate("/", { replace: true });
    } catch (err) {
      setError(err.message || "No pudimos completar el registro. Intentá de nuevo.");
      setEnviando(false);
    }
  };

  return (
    <AuthLayout
      panelIzquierdo={<img src={autoImg} alt="Autos en venta en CarFind" />}
      panelDerecho={
        <>
          <img src={autoImg} alt="Encontrá tu próximo auto" />
          <button className="clear-filters-btn" onClick={() => navigate("/reportes")}>¿Necesitás ayuda?</button>
        </>
      }
    >
      <h2>TU VIAJE EMPIEZA ACÁ</h2>
      <p>CON NOSOTROS</p>

      <form className="auth-form" onSubmit={handleSubmit}>
        <input 
          className="search-bar"
          name="dni"
          type="text"
          placeholder="DNI"
          inputMode="numeric"
          pattern="[0-9]{7,8}"
          title="7 u 8 números, sin puntos"
          value={formData.dni}
          onChange={handleChange} 
          required
        />
        <input
          className="search-bar"
          name="email"
          type="email"
          placeholder="Correo Electrónico"
          value={formData.email}
          onChange={handleChange}
          required
        />
        <input
          className="search-bar"
          name="nombre"
          type="text" 
          placeholder="Nombre y Apellido"
          value={formData.nombre}
          onChange={handleChange}
          required
        />
        <input 
          className="search-bar"
          name="telefono"
          type="tel" 
          placeholder="Número telefónico"
          value={formData.telefono}
          onChange={handleChange}
          required
        />
        <input 
          className="search-bar"
          name="password"
          type="password"
          placeholder="Contraseña (mínimo 8 caracteres)"
          minLength={8}
          value={formData.password}
          onChange={handleChange}
          required
        />
        {error && <p className="perfil-error" role="alert">{error}</p>}
        <button type="submit" className="navButton" disabled={enviando}>
          {enviando ? "Registrando..." : "Completar Registro"}
        </button>
        <small className="terminos-aviso">
          Al registrarte aceptás los <Link to="/terminos">Términos y Condiciones</Link>.
        </small>
      </form>
      
      <button className="invitado-btn" onClick={() => navigate("/")}>
        Ingresar como invitado
      </button>
    </AuthLayout>
  );
};

export default Registro;