package com.erikthegod.practica4.persistencia;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 *
 * @author kinton
 */
public class GestorArchivos {

    public PrintWriter fichero;
    public Document documento;
    public PdfPTable tabla;
    public static final String AUTOR_PDF = "ErikTheGod";

    public void generarPDF(File nombreArchivo) throws FileNotFoundException, DocumentException {
        FileOutputStream archivo = null;
        documento = new Document();
        archivo = new FileOutputStream(nombreArchivo + ".pdf");
        PdfWriter.getInstance(documento, archivo);
        documento.open();
        documento.addAuthor(AUTOR_PDF);
    }

    public void exportarHTML(File nombreArchivo) throws FileNotFoundException {
        fichero = new PrintWriter(nombreArchivo + ".html");
        fichero.println("<!DOCTYPE html>");
        fichero.println("<html>");
        fichero.println("<head>");
        fichero.println("<title> ErikTheGod </title>");
        fichero.println("</head>");
        fichero.println("<body bgcolor=\"blue\" text=\"white\">");
        fichero.println("<table border=\"5\" rules=\"all\">");
        fichero.println("<tr>");
        fichero.println("<td> Nombre </td>");
        fichero.println("<td> Categoria </td>");
        fichero.println("<td> Precio </td>");
        fichero.println("<td> Id Pedido </td>");
        fichero.println("</tr>");
    }
}
