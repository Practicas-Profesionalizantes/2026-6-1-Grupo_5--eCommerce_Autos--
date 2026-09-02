import React from "react";

// Cargamos las 4 imágenes desde src/assets/carrousel-img usando require
const slides = [
  { key: "economico", label: "Económico", img: require("../assets/carrousel-img/economico.jpg") },
  { key: "electrico", label: "Eléctrico", img: require("../assets/carrousel-img/electrico.jpg") },
  { key: "suv", label: "SUV", img: require("../assets/carrousel-img/suv.jpg") },
  { key: "lujo", label: "De lujo", img: require("../assets/carrousel-img/xlujo.jpg") }, 
];

const ImageGallery = ({ onSelectCategoria }) => {
  return (
    <div className="carousel-wrapper" style={{ display: "flex", flexDirection: "column", alignItems: "center", gap: 12 }}>
      <div style={{ display: "flex", gap: 12 }}>
        {slides.map((s) => (
          <button
            key={s.key}
            onClick={() => onSelectCategoria(s.key)}
            style={{
              border: "none",
              padding: 0,
              background: "transparent",
              cursor: "pointer",
              borderRadius: 10,
              overflow: "hidden",
              boxShadow: "0 4px 12px rgba(0,0,0,0.08)"
            }}
            aria-label={`Ver categoría ${s.label}`}
          >
            <img
              src={s.img}
              alt={s.label}
              style={{ width: 220, height: 140, objectFit: "cover", display: "block" }}
            />
            <div style={{ padding: "8px 12px", background: "#fff", textAlign: "center", fontWeight: 700, color: "#04926f" }}>
              {s.label}
            </div>
          </button>
        ))}
      </div>

      <div className="carousel-note" style={{ color: "#666", fontSize: 14 }}>
        Al hacer clic en una imagen del carrusel se filtran los autos por esa categoría.
      </div>
    </div>
  );
};

export default ImageGallery;