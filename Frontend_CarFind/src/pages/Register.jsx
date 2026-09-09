import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import AuthLayout from "../components/AuthLayout";
import "../assets/styles/App.css";

const Registro = () => {
  const [formData, setFormData] = useState({
    dni: "",
    email: "",
    nombre: "",
    telefono: "",
    password: "",
  });

  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Registro enviado:", formData);
    navigate("/login");
  };

  return (
    <AuthLayout
      panelIzquierdo={<p>imagen de autitos</p>}
      panelDerecho={
        <>
          <p>imagen del rayo mcqueen</p>
          <button className="clear-filters-btn">Obtener ayuda ?</button>
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
          placeholder="Contraseña"
          value={formData.password}
          onChange={handleChange}
          required
        />
        <button type="submit" className="navButton">Completar Registro</button>
      </form>
      
      <button className="invitado-btn" onClick={() => navigate("/")}>
        Ingresar como invitado
      </button>
    </AuthLayout>
  );
};

export default Registro;