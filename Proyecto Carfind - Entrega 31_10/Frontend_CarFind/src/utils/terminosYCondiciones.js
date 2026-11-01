/**
 * Contenido de la página "Términos y Condiciones".
 *
 * Para agregar un artículo, sumar un objeto a `articulos` con su número, título y cláusulas:
 * las cláusulas se numeran solas (1.1, 1.2, ...). Cuando hay más de un artículo,
 * la página muestra un índice.
 *
 * Por ahora solo está cargado el Artículo 1. El texto legal definitivo debe ser revisado
 * por quien corresponda en el equipo antes de publicarse.
 */
export const TERMINOS = {
  titulo: 'TÉRMINOS Y CONDICIONES DE USO DE E-commerce CARFIND',
  articulos: [
    {
      numero: 1,
      titulo: 'Disposiciones generales',
      clausulas: [
        'CarFind es una plataforma digital destinada a facilitar la búsqueda, publicación y consulta de vehículos, permitiendo que compradores y vendedores particulares entren en contacto para realizar posibles operaciones de compraventa.',
        'Estos Términos y Condiciones regulan el acceso y la utilización de la plataforma, así como los derechos y obligaciones de sus usuarios.',
        'Al registrarse, acceder a las funciones que requieren una cuenta o utilizar los servicios de CarFind, el usuario declara haber leído y aceptado estos Términos y Condiciones.',
        'CarFind no es una concesionaria, aseguradora, entidad financiera, intermediaria de pagos ni parte de los contratos de compraventa celebrados entre los usuarios. Su función se limita a proporcionar un espacio digital para publicar vehículos, consultar anuncios y facilitar el contacto entre particulares.',
        'La plataforma no vende vehículos por cuenta propia, no recibe el precio de las operaciones ni cobra comisiones por las compraventas realizadas entre usuarios.',
      ],
    },
  ],
};
