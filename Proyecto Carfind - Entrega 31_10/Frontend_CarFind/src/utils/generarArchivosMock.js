/**
 * Genera archivos de prueba (CSV y PDF) en el navegador.
 * Lo usa SOLO adminService.mock.js para simular la descarga de informes:
 * con el backend real, los archivos los genera el servidor.
 */

/** CSV con BOM para que Excel respete los acentos. */
export function generarCsv(columnas, filas) {
  const celda = (v) => `"${String(v ?? '').replace(/"/g, '""')}"`;
  const lineas = [columnas.map(celda).join(','), ...filas.map((f) => f.map(celda).join(','))];
  return new Blob([`\uFEFF${lineas.join('\r\n')}`], { type: 'text/csv;charset=utf-8' });
}

/** PDF de una página con un título y líneas de texto (Helvetica, solo caracteres latinos). */
export function generarPdfSimple(titulo, lineas) {
  const MAX_LINEAS = 48;
  const limpiar = (t) => t.replace(/[^\x00-\xFF]/g, '?'); // una letra = un byte
  const esc = (t) => limpiar(t).replace(/\\/g, '\\\\').replace(/\(/g, '\\(').replace(/\)/g, '\\)');

  const visibles = lineas.slice(0, MAX_LINEAS);
  if (lineas.length > MAX_LINEAS) visibles.push(`... y ${lineas.length - MAX_LINEAS} más`);

  let y = 740;
  let contenido = `BT /F1 16 Tf 50 790 Td (${esc(titulo)}) Tj ET\n`;
  visibles.forEach((linea) => {
    contenido += `BT /F1 10 Tf 50 ${y} Td (${esc(linea)}) Tj ET\n`;
    y -= 14;
  });

  const objetos = [
    '<< /Type /Catalog /Pages 2 0 R >>',
    '<< /Type /Pages /Kids [3 0 R] /Count 1 >>',
    '<< /Type /Page /Parent 2 0 R /MediaBox [0 0 595 842] /Resources << /Font << /F1 4 0 R >> >> /Contents 5 0 R >>',
    '<< /Type /Font /Subtype /Type1 /BaseFont /Helvetica /Encoding /WinAnsiEncoding >>',
    `<< /Length ${contenido.length} >>\nstream\n${contenido}endstream`,
  ];

  let pdf = '%PDF-1.4\n';
  const posiciones = [];
  objetos.forEach((cuerpo, i) => {
    posiciones.push(pdf.length);
    pdf += `${i + 1} 0 obj\n${cuerpo}\nendobj\n`;
  });
  const inicioXref = pdf.length;
  pdf += `xref\n0 ${objetos.length + 1}\n0000000000 65535 f \n`;
  posiciones.forEach((p) => {
    pdf += `${String(p).padStart(10, '0')} 00000 n \n`;
  });
  pdf += `trailer\n<< /Size ${objetos.length + 1} /Root 1 0 R >>\nstartxref\n${inicioXref}\n%%EOF`;

  return new Blob([Uint8Array.from(pdf, (c) => c.charCodeAt(0))], { type: 'application/pdf' });
}
