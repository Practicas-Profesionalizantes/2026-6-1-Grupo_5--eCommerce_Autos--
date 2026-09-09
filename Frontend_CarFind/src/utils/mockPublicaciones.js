import corollaImg from "../assets/images/CorollaEjemplo.jpg";
import autoImg1 from "../assets/images/auto.jpg";
import autoImg2 from "../assets/images/Auto2.jpg";

export const PUBLICACION_DETALLE_MOCK = {
  id: 1,
  titulo: "Renault Kangoo 1.6 Authentique",
  precio: 18500000,
  moneda: "ARS",
  ubicacion: "Buenos Aires, Argentina",
  anio: 2018,
  kms: 180500,
  publicadoHace: "Publicado hace 1 día",
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
  imagenes: [corollaImg, autoImg1, autoImg2, corollaImg, autoImg1],
};

export const PRECIOS_REFERENCIA_MOCK = [
  { id: 101, titulo: "Mismo modelo y versión", precio: "ARS 18.200.000 - Ver detalle", img: autoImg1 },
  { id: 102, titulo: "Mismo modelo y año", precio: "ARS 18.600.000 - Ver detalle", img: autoImg2 },
  { id: 103, titulo: "Mismo modelo / mayor km", precio: "ARS 17.900.000 - Ver detalle", img: corollaImg },
  { id: 104, titulo: "Misma marca y rango", precio: "ARS 19.000.000 - Ver detalle", img: autoImg1 },
];

export const VEHICULOS_RELACIONADOS_MOCK = [1, 2, 3, 4, 5, 6].map((num) => ({
  id: num + 20,
  titulo: `Opción similar ${num}`,
  precio: 18000000 + num * 200000,
  vendedor: "Verificado",
}));

export const PUBLICACIONES_HOME_MOCK = [1, 2, 3, 4, 5, 6, 7, 8].map((num) => ({
  id: num,
  titulo: "Corolla 2017",
  precio: 19235000,
  vendedor: `Concesionaria ${num}`,
}));