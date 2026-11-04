import React, { useEffect, useState } from "react";
import { useSearchParams } from "react-router-dom";
import ProductCard from "../components/ProductCard";
import FiltrosBar from "../components/FiltrosBar";
import { getPublicaciones, getOpcionesFiltros } from "../services/publicacionService";
import "../assets/styles/App.css";
import "../assets/styles/variables.css";

const FILTROS_VACIOS = { marca: "", modelo: "", anio: "", kmsMax: "", precioMax: "" };

const OPCIONES_KM = [10000, 50000, 100000, 150000, 200000].map((km) => ({
  valor: km,
  texto: `Hasta ${km.toLocaleString("es-AR")} km`,
}));

const OPCIONES_PRECIO = [5000000, 10000000, 20000000, 30000000, 50000000].map((precio) => ({
  valor: precio,
  texto: `Hasta $${precio.toLocaleString("es-AR")}`,
}));

const aOpciones = (valores = []) => valores.map((v) => ({ valor: v, texto: String(v) }));

function Home() {
  const [searchParams] = useSearchParams();
  const busqueda = searchParams.get("q") ?? "";
  const [filtros, setFiltros] = useState(FILTROS_VACIOS);
  const [opciones, setOpciones] = useState({ marca: [], modelo: [], anio: [] });
  // Resultado de la última búsqueda, junto con los filtros que la originaron.
  const [resultado, setResultado] = useState({ clave: null, publicaciones: [], error: null });
  const consulta = { ...filtros, q: busqueda };
  const clave = JSON.stringify(consulta);
  const loading = resultado.clave !== clave;
  const { publicaciones, error } = resultado;

  useEffect(() => {
    getOpcionesFiltros().then(setOpciones).catch(() => {}); // sin opciones, los filtros quedan en "Todos"
  }, []);

  useEffect(() => {
    let vigente = true; // evita pisar resultados si cambian los filtros antes de que llegue la respuesta
    getPublicaciones(JSON.parse(clave))
      .then((datos) => vigente && setResultado({ clave, publicaciones: datos, error: null }))
      .catch((e) => vigente && setResultado({ clave, publicaciones: [], error: e.message || "No pudimos cargar las publicaciones." }));
    return () => {
      vigente = false;
    };
  }, [clave]);

  const handleFiltroChange = (e) => {
    const { name, value } = e.target;
    setFiltros((prev) => ({ ...prev, [name]: value }));
  };

  const handleLimpiarFiltros = () => {
    setFiltros(FILTROS_VACIOS);
  };

  const campos = [
    { nombre: "marca", etiqueta: "Marca", opciones: aOpciones(opciones.marca) },
    { nombre: "modelo", etiqueta: "Modelo", opciones: aOpciones(opciones.modelo) },
    { nombre: "anio", etiqueta: "Año", opciones: aOpciones(opciones.anio) },
    { nombre: "kmsMax", etiqueta: "Kilometraje", opciones: OPCIONES_KM },
    { nombre: "precioMax", etiqueta: "Precio", opciones: OPCIONES_PRECIO },
  ];

  return (
    <>
      <FiltrosBar 
        campos={campos}
        filtrosSeleccionados={filtros} 
        onFiltroChange={handleFiltroChange} 
        onLimpiarFiltros={handleLimpiarFiltros} 
      />
      {busqueda && <p className="wishlist-status">Resultados para "{busqueda}"</p>}
      <main className="content-grid">
        {loading && <p className="wishlist-status">Cargando publicaciones...</p>}
        {!loading && error && <p className="wishlist-error" role="alert">{error}</p>}
        {!loading && !error && publicaciones.length === 0 && (
          <p className="wishlist-status">No encontramos publicaciones con esos filtros.</p>
        )}
        {!loading && !error && publicaciones.map((pub) => (
          <ProductCard key={pub.id} publicacion={pub} />
        ))}
      </main>
    </>
  );
}

export default Home;
