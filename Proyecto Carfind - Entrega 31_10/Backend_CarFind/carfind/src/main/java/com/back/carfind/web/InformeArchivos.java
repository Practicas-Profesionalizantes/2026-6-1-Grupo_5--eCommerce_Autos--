package com.back.carfind.web;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/** Arma los archivos de los informes del panel de administración (CSV para Excel y PDF simple de una página). */
final class InformeArchivos {

    private static final int MAX_LINEAS_PDF = 48;

    private InformeArchivos() {
    }

    /** CSV con BOM para que Excel respete los acentos. */
    static byte[] csv(List<String> columnas, List<List<Object>> filas) {
        List<String> lineas = new ArrayList<>();
        lineas.add(columnas.stream().map(InformeArchivos::celda).collect(Collectors.joining(",")));
        filas.forEach(f -> lineas.add(f.stream().map(InformeArchivos::celda).collect(Collectors.joining(","))));
        return ("﻿" + String.join("\r\n", lineas)).getBytes(StandardCharsets.UTF_8);
    }

    /** PDF de una página con un título y líneas de texto (Helvetica, caracteres latinos). */
    static byte[] pdf(String titulo, List<String> lineas) {
        List<String> visibles = new ArrayList<>(lineas.subList(0, Math.min(lineas.size(), MAX_LINEAS_PDF)));
        if (lineas.size() > MAX_LINEAS_PDF) visibles.add("... y " + (lineas.size() - MAX_LINEAS_PDF) + " más");

        StringBuilder contenido = new StringBuilder("BT /F1 16 Tf 50 790 Td (" + escapar(titulo) + ") Tj ET\n");
        int y = 740;
        for (String linea : visibles) {
            contenido.append("BT /F1 10 Tf 50 ").append(y).append(" Td (").append(escapar(linea)).append(") Tj ET\n");
            y -= 14;
        }

        List<String> objetos = List.of(
                "<< /Type /Catalog /Pages 2 0 R >>",
                "<< /Type /Pages /Kids [3 0 R] /Count 1 >>",
                "<< /Type /Page /Parent 2 0 R /MediaBox [0 0 595 842] /Resources << /Font << /F1 4 0 R >> >> /Contents 5 0 R >>",
                "<< /Type /Font /Subtype /Type1 /BaseFont /Helvetica /Encoding /WinAnsiEncoding >>",
                "<< /Length " + contenido.length() + " >>\nstream\n" + contenido + "endstream");

        StringBuilder pdf = new StringBuilder("%PDF-1.4\n");
        List<Integer> posiciones = new ArrayList<>();
        for (int i = 0; i < objetos.size(); i++) {
            posiciones.add(pdf.length());
            pdf.append(i + 1).append(" 0 obj\n").append(objetos.get(i)).append("\nendobj\n");
        }
        int inicioXref = pdf.length();
        pdf.append("xref\n0 ").append(objetos.size() + 1).append("\n0000000000 65535 f \n");
        posiciones.forEach(p -> pdf.append(String.format("%010d 00000 n \n", p)));
        pdf.append("trailer\n<< /Size ").append(objetos.size() + 1).append(" /Root 1 0 R >>\nstartxref\n")
                .append(inicioXref).append("\n%%EOF");
        // Una letra = un byte: por eso se limpia todo lo que no entra en Latin-1.
        return pdf.toString().getBytes(StandardCharsets.ISO_8859_1);
    }

    private static String celda(Object valor) {
        return "\"" + Json.texto(valor).replace("\"", "\"\"") + "\"";
    }

    private static String escapar(String texto) {
        return texto.replaceAll("[^\\x00-\\xFF]", "?").replace("\\", "\\\\").replace("(", "\\(").replace(")", "\\)");
    }
}
