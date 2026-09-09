import React, { useState } from "react";
import Header from "../components/Header";
import ProductCard from "../components/ProductCard";
import FiltrosBar from "../components/FiltrosBar";
import { PUBLICACIONES_HOME_MOCK } from "../utils/mockPublicaciones";
import "../assets/styles/App.css";
import "../assets/styles/variables.css";

function Home() {
  const [filtros, setFiltros] = useState({
    marca: "",
    modelo: "",
    anio: "",
    kilometraje: "",
    precio: ""
  });

  const handleFiltroChange = (e) => {
    const { name, value } = e.target;
    setFiltros((prev) => ({ ...prev, [name]: value }));
  };

  const handleLimpiarFiltros = () => {
    setFiltros({ marca: "", modelo: "", anio: "", kilometraje: "", precio: "" });
  };

  return (
    <div className="app-wrapper">
      <Header />
      <FiltrosBar 
        filtrosSeleccionados={filtros} 
        onFiltroChange={handleFiltroChange} 
        onLimpiarFiltros={handleLimpiarFiltros} 
      />
      <main className="content-grid">
        {PUBLICACIONES_HOME_MOCK.map((pub) => (
          <ProductCard key={pub.id} publicacion={pub} />
        ))}
      </main>
    </div>
  );
}

export default Home;