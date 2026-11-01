import React, { useEffect, useState } from "react";
import { useParams, Link, useNavigate } from "react-router-dom";
import ProductCard from "../components/ProductCard";
import PreciosReferencia from "../components/PreciosReferencia";
import EspecificacionesVehiculo from "../components/EspecificacionesVehiculo";
import GaleriaPrevisualizacion from "../components/GaleriaPrevisualizacion";
import { PRECIOS_REFERENCIA_MOCK } from "../utils/mockPublicaciones";
import useWishlist from "../hooks/useWishlist";
import usePublicacion from "../hooks/usePublicacion";
import useAuth from "../hooks/useAuth";
import { iniciarConversacion } from "../services/mensajeriaService";
import { getPublicaciones } from "../services/publicacionService";
import { imagenAuto } from "../utils/imagenAuto";
import AccionesPublicacion from "../components/AccionesPublicacion";
import "../assets/styles/App.css";

function PublicacionDetalle({ id }) {
  const { publicacion: datosPublicacion, loading, error, reload } = usePublicacion(id);
  const [fotoPrincipalIndex, setFotoPrincipalIndex] = useState(0);
  const { add, isInWishlist, loading: wishlistLoading, pendingId, error: wishlistError } = useWishlist();
  const navigate = useNavigate();
  const [iniciandoChat, setIniciandoChat] = useState(false);
  const [chatError, setChatError] = useState(null);
  const [relacionados, setRelacionados] = useState([]);
  const { usuario } = useAuth();
  const marca = datosPublicacion?.caracteristicas?.marca;

  // "Estos vehículos también podrían interesarte": otras publicaciones de la misma marca.
  useEffect(() => {
    if (!marca) return;
    getPublicaciones({ marca, excluir: id, limite: 6 })
      .then(setRelacionados)
      .catch(() => setRelacionados([]));
  }, [marca, id]);

  if (loading) {
    return (
      <main className="publicacion-container">
        <p className="wishlist-status">Cargando publicación...</p>
      </main>
    );
  }

  if (error || !datosPublicacion) {
    return (
      <main className="publicacion-container">
        <div className="wishlist-error" role="alert">
          {error} <button type="button" className="btn-support" onClick={reload}>Reintentar</button>
          <Link to="/">Volver al inicio</Link>
        </div>
      </main>
    );
  }

  // "Preguntar": abre (o crea) la conversación de esta publicación en Mensajería.
  const preguntar = async () => {
    if (!usuario) {
      navigate("/login", { state: { desde: `/publicacion/${datosPublicacion.id}` } });
      return;
    }
    setIniciandoChat(true);
    setChatError(null);
    try {
      const { conversacionId } = await iniciarConversacion(datosPublicacion.id);
      navigate(`/mensajes/${conversacionId}`);
    } catch (e) {
      setChatError(e.message || "No pudimos iniciar el chat. Intentá de nuevo.");
      setIniciandoChat(false);
    }
  };

  const agregarAWishlist = () => {
    if (!usuario) {
      navigate("/login", { state: { desde: `/publicacion/${datosPublicacion.id}` } });
      return;
    }
    add(datosPublicacion.id);
  };

  const telefonoWhatsApp = (datosPublicacion.publicante?.telefono ?? "").replace(/\D/g, "");
  const abrirWhatsApp = () => {
    const mensaje = encodeURIComponent(`Hola, estoy interesado en tu publicación: ${datosPublicacion.titulo}`);
    window.open(`https://wa.me/${telefonoWhatsApp}?text=${mensaje}`, "_blank", "noopener,noreferrer");
  };

  const yaGuardada = isInWishlist(datosPublicacion.id);
  const guardando = pendingId === datosPublicacion.id;

  return (
    <main className="publicacion-container">
      <section className="publicacion-main-grid">
        
        <GaleriaPrevisualizacion 
          fotos={datosPublicacion.imagenes.length > 0 ? datosPublicacion.imagenes : [imagenAuto(null)]}
          fotoPrincipalIndex={fotoPrincipalIndex}
          onSelectFoto={setFotoPrincipalIndex}
          precio={datosPublicacion.precio}
        />

        <div className="info-column">
          <div className="metadata-bar">
            {datosPublicacion.anio} | {Number(datosPublicacion.kms ?? 0).toLocaleString("es-AR")} km - {datosPublicacion.publicadoHace}
          </div>

          <h1 className="product-title">{datosPublicacion.titulo}</h1>

          <div className="price-location-box">
            <span className="location-tag">{datosPublicacion.ubicacion}</span>
            <span className="price-tag">
              {datosPublicacion.moneda} ${datosPublicacion.precio.toLocaleString("es-AR")}
            </span>
          </div>

          {datosPublicacion.publicante && (
            <div className="publicante-box">
              <span>Publicado por</span>
              <Link to={`/usuario/${datosPublicacion.publicante.id}`} className="publicante-link">
                {datosPublicacion.publicante.nombre} {datosPublicacion.publicante.apellido}
              </Link>
              <span aria-hidden="true">·</span>
              <Link to={`/publicacion/${datosPublicacion.id}/reportar`} className="publicante-reportar">
                Reportar publicación
              </Link>
            </div>
          )}

          <EspecificacionesVehiculo caracteristicas={datosPublicacion.caracteristicas} />

          {datosPublicacion.descripcion && (
            <div className="specs-card">
              <h3>Descripción</h3>
              <p style={{ whiteSpace: "pre-line" }}>{datosPublicacion.descripcion}</p>
            </div>
          )}

          {datosPublicacion.esPropia ? (
            <div className="publicacion-propia">
              <p className="publicacion-propia-texto">
                {datosPublicacion.estado === "PAUSADA"
                  ? "Esta publicación está pausada: solo la ves vos. Reactivala para que vuelva a aparecer."
                  : "Esta es tu publicación."}
              </p>
              <AccionesPublicacion
                publicacion={datosPublicacion}
                onCambio={(cambio) => (cambio.tipo === "eliminada" ? navigate("/perfil") : reload())}
              />
            </div>
          ) : (
            <div className="contact-actions">
              <button className="btn-ask" onClick={preguntar} disabled={iniciandoChat}>
                {iniciandoChat ? "Abriendo chat..." : "Preguntar"}
              </button>
              <button className="btn-ws" onClick={abrirWhatsApp} disabled={!telefonoWhatsApp}>
                WhatsApp
              </button>
              <button
                className="btn-wishlist"
                onClick={agregarAWishlist}
                disabled={wishlistLoading || guardando || yaGuardada}
              >
                {yaGuardada ? "❤️ En tu Wishlist" : guardando ? "Agregando..." : "🤍 Agregar a Wishlist"}
              </button>
            </div>
          )}
          {wishlistError && <small className="wishlist-error" role="alert">{wishlistError}</small>}
          {chatError && <small className="wishlist-error" role="alert">{chatError}</small>}
        </div>
      </section>

      <section className="publicacion-secondary-grid">
        {relacionados.length > 0 && (
          <div className="recommended-section">
            <h2>Estos vehículos también podrían interesarte</h2>
            <div className="content-grid">
              {relacionados.map((item) => (
                <ProductCard key={item.id} publicacion={item} />
              ))}
            </div>
          </div>
        )}

        <PreciosReferencia listaPrecios={PRECIOS_REFERENCIA_MOCK} publicacionId={datosPublicacion.id} />
      </section>
    </main>
  );
}

/**
 * Detalle de una publicación. El `key` por id hace que al pasar de una publicación
 * a otra (por ejemplo desde "También podrían interesarte") la pantalla arranque limpia:
 * vuelve a cargar los datos y la galería a la primera foto.
 */
function Publicacion() {
  const { id } = useParams();
  return <PublicacionDetalle key={id} id={id} />;
}

export default Publicacion;
