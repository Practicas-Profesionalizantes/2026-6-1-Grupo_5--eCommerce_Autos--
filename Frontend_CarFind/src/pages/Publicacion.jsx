import React, { useState } from "react";
import { useParams } from "react-router-dom";
import Header from "../components/Header";
import ProductCard from "../components/ProductCard";
import PreciosReferencia from "../components/PreciosReferencia";
import EspecificacionesVehiculo from "../components/EspecificacionesVehiculo";
import GaleriaPrevisualizacion from "../components/GaleriaPrevisualizacion";
import { 
  PUBLICACION_DETALLE_MOCK, 
  PRECIOS_REFERENCIA_MOCK, 
  VEHICULOS_RELACIONADOS_MOCK 
} from "../utils/mockPublicaciones";
import "../assets/styles/App.css";

function Publicacion() {
  const { id } = useParams();
  const [datosPublicacion] = useState(PUBLICACION_DETALLE_MOCK);
  const [fotoPrincipalIndex, setFotoPrincipalIndex] = useState(0);

  return (
    <div className="app-wrapper">
      <Header />

      <main className="publicacion-container">
        <section className="publicacion-main-grid">
          
          <GaleriaPrevisualizacion 
            fotos={datosPublicacion.imagenes}
            fotoPrincipalIndex={fotoPrincipalIndex}
            onSelectFoto={setFotoPrincipalIndex}
            precio={datosPublicacion.precio}
          />

          <div className="info-column">
            <div className="metadata-bar">
              {datosPublicacion.anio} | {datosPublicacion.kms.toLocaleString()} km - {datosPublicacion.publicadoHace}
            </div>

            <h1 className="product-title">{datosPublicacion.titulo}</h1>

            <div className="price-location-box">
              <span className="location-tag">{datosPublicacion.ubicacion}</span>
              <span className="price-tag">
                {datosPublicacion.moneda} ${datosPublicacion.precio.toLocaleString("es-AR")}
              </span>
            </div>

            <EspecificacionesVehiculo caracteristicas={datosPublicacion.caracteristicas} />

            <div className="contact-actions">
              <button className="btn-ask" onClick={() => alert("Modal/chat en desarrollo")}>
                Preguntar
              </button>
              <button className="btn-ws" onClick={() => alert("Redirigiendo a WhatsApp...")}>
                WhatsApp
              </button>
            </div>
          </div>
        </section>

        <section className="publicacion-secondary-grid">
          <div className="recommended-section">
            <h2>Estos vehículos también podrían interesarte</h2>
            <div className="content-grid">
              {VEHICULOS_RELACIONADOS_MOCK.map((item) => (
                <ProductCard key={item.id} publicacion={item} />
              ))}
            </div>
          </div>

          <PreciosReferencia listaPrecios={PRECIOS_REFERENCIA_MOCK} />
        </section>
      </main>
    </div>
  );
}

export default Publicacion;