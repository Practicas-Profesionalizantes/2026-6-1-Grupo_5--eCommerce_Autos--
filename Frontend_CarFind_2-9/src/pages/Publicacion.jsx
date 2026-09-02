import React, { useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import ProductCard from "../components/ProductCard";
import corollaImg from "../assets/images/CorollaEjemplo.jpg";
import autoImg1 from "../assets/images/auto (1).jpg";
import autoImg2 from "../assets/images/Auto2 (1).jpg";
import "../assets/styles/App.css";

function Publicacion() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [isDarkMode, setIsDarkMode] = useState(false);

  // Mapeo alineado con las tablas: publicaciones, autos, marcas, modelos, etc.
  const [datosPublicacion, setDatosPublicacion] = useState({
    id: id || 1,
    titulo: "Renault Kangoo 1.6 Authentique",
    precio: 18500000,
    moneda: "ARS",
    ubicacion: "Buenos Aires, Argentina",
    anio: 2018,
    kms: 180500,
    publicadoHace: "Publicado hace 1 día",
    // Tabla 'autos'
    caracteristicas: {
      marca: "Renault",
      modelo: "Kangoo",
      versionn: "1.6 Authentique",
      anio: 2018,
      motor: "1.6",
      transmision: "Manual",
      combustible: "Nafta",
      kilometraje: 180500,
      color: "Gris",
      vtv: true,
      grabado: true,
      papelesAlDia: "Sí",
    },
    // Tabla 'multimedia'
    imagenes: [corollaImg, autoImg1, autoImg2, corollaImg, autoImg1],
  });

  const [imagenSeleccionada, setImagenSeleccionada] = useState(
    datosPublicacion.imagenes[0]
  );

  // Mock de Precios de Referencia (calculados o jerarquizados desde backend)
  const preciosReferencia = [
    { id: 101, titulo: "Mismo modelo y versión", precio: "ARS 18.200.000 - Ver detalle", img: autoImg1 },
    { id: 102, titulo: "Mismo modelo y año", precio: "ARS 18.600.000 - Ver detalle", img: autoImg2 },
    { id: 103, titulo: "Mismo modelo / mayor km", precio: "ARS 17.900.000 - Ver detalle", img: corollaImg },
    { id: 104, titulo: "Misma marca y rango", precio: "ARS 19.000.000 - Ver detalle", img: autoImg1 },
  ];

  // Mock de Vehículos Relacionados (para ProductCard)
  const vehiculosRelacionados = [1, 2, 3, 4, 5, 6].map((num) => ({
    id: num + 20,
    titulo: `Opción similar ${num}`,
    precio: 18000000 + num * 200000,
    vendedor: "Verificado",
  }));

  const toggleTheme = () => setIsDarkMode(!isDarkMode);

  return (
    <div className="app-wrapper" data-theme={isDarkMode ? "dark" : "light"}>
      {/* HEADER */}
      <header className="header">
        <div className="logo">
          <Link to="/" style={{ textDecoration: "none", color: "inherit", fontWeight: "bold" }}>
            CarFind
          </Link>
        </div>
        <input className="search-bar" type="text" placeholder="Buscar auto..." />
        <div className="actions">
          <button onClick={toggleTheme} className="icon-button">{isDarkMode ? "🌙" : "☀️"}</button>
          <button className="icon-button">🛒</button>
          <button className="icon-button">🔔</button>
          <button className="icon-button">💬</button>
          <button className="icon-button">👤</button>
        </div>
        <div className="login">
          <button className="navButton" onClick={() => navigate("/login")}>Iniciar Sesión</button>
          <button className="navButton" onClick={() => navigate("/register")}>Registrarse</button>
        </div>
      </header>

      {/* CONTENIDO PRINCIPAL */}
      <main className="publicacion-container">
        
        {/* SECCIÓN SUPERIOR: Galería e Información Principal */}
        <section className="publicacion-main-grid">
          
          {/* GALERÍA DE IMÁGENES */}
          <div className="gallery-container">
            <div className="main-image-box">
              <img src={imagenSeleccionada} alt={datosPublicacion.titulo} />
            </div>
            
            <div className="thumbnails-column">
              {datosPublicacion.imagenes.map((img, index) => (
                <button
                  key={index}
                  className={`thumb-btn ${imagenSeleccionada === img ? "active" : ""}`}
                  onClick={() => setImagenSeleccionada(img)}
                >
                  <img src={img} alt={`Miniatura ${index + 1}`} />
                </button>
              ))}
            </div>
          </div>

          {/* INFORMACIÓN DEL PRODUCTO */}
          <div className="info-column">
            <div className="metadata-bar">
              {datosPublicacion.anio} | {datosPublicacion.kms.toLocaleString()} km - {datosPublicacion.publicadoHace}
            </div>

            <h1 className="product-title">{datosPublicacion.titulo}</h1>

            <div className="price-location-box">
              <span className="location-tag">📍 {datosPublicacion.ubicacion}</span>
              <span className="price-tag">
                {datosPublicacion.moneda} ${datosPublicacion.precio.toLocaleString('es-AR')}
              </span>
            </div>

            {/* CARACTERÍSTICAS DEL PRODUCTO */}
            <div className="specs-card">
              <h3>Características del producto</h3>
              <div className="specs-grid">
                <div>
                  <p><strong>Marca / Modelo:</strong> {datosPublicacion.caracteristicas.marca} - {datosPublicacion.caracteristicas.modelo}</p>
                  <p><strong>Año:</strong> {datosPublicacion.caracteristicas.anio}</p>
                  <p><strong>Motor:</strong> {datosPublicacion.caracteristicas.motor}</p>
                  <p><strong>Transmisión:</strong> {datosPublicacion.caracteristicas.transmision}</p>
                  <p><strong>Tipo de combustible:</strong> {datosPublicacion.caracteristicas.combustible}</p>
                  <p><strong>Papeles al día:</strong> {datosPublicacion.caracteristicas.papelesAlDia}</p>
                </div>
                <div>
                  <p><strong>Kilometraje:</strong> {datosPublicacion.caracteristicas.kilometraje.toLocaleString()} km</p>
                  <p><strong>Color:</strong> {datosPublicacion.caracteristicas.color}</p>
                  <h4 style={{ margin: "10px 0 5px 0" }}>Otras Especificaciones:</h4>
                  <p>VTV: {datosPublicacion.caracteristicas.vtv ? "Sí" : "No"}</p>
                  <p>Grabado de autopartes: {datosPublicacion.caracteristicas.grabado ? "Sí" : "No"}</p>
                </div>
              </div>
            </div>

            {/* ACCIONES DE CONTACTO */}
            <div className="contact-actions">
              <button className="btn-ask" onClick={() => alert("Función para abrir modal/chat de Preguntar")}>
                Preguntar
              </button>
              <button className="btn-ws" onClick={() => alert("Redirigiendo a WhatsApp...")}>
                WhatsApp
              </button>
            </div>
          </div>
        </section>

        {/* SECCIÓN INTERMEDIA: Recomendados y Precios de referencia */}
        <section className="publicacion-secondary-grid">
          
          {/* LADO IZQUIERDO: Vehículos recomendados */}
          <div className="recommended-section">
            <h2>Estos vehículos también podrían interesarte</h2>
            <div className="content-grid">
              {vehiculosRelacionados.map((item) => (
                <ProductCard key={item.id} publicacion={item} />
              ))}
            </div>
          </div>

          {/* LADO DERECHO: Precios de Referencia */}
          <div className="reference-prices-section">
            <h2>Precios de referencia</h2>
            <div className="reference-box">
              {preciosReferencia.map((ref) => (
                <div key={ref.id} className="reference-item">
                  <div className="ref-thumb">
                    <img src={ref.img} alt={ref.titulo} />
                  </div>
                  <div className="ref-details">
                    <p className="ref-title">{ref.titulo}</p>
                    <p className="ref-price">{ref.precio}</p>
                  </div>
                </div>
              ))}
            </div>

            {/* REPORTE / SOPORTE */}
            <div className="support-box">
              <small>¿Tuviste un problema con la publicación? <a href="#reportar">Avisanos</a>.</small>
              <button className="btn-support">SOPORTE/REPORTAR</button>
            </div>
          </div>

        </section>
      </main>
    </div>
  );
}

export default Publicacion;