package com.erikthegod.practica4.modelopersistencia;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Clase que maneja ficheros
 *
 * @author ErikTheGod
 */
public class GestorArchivos {

    public FileOutputStream archivo = null;//Declaracion archivo
    public PrintWriter fichero;//Declaracion fichero
    public Document documento;//Declaracion documento pdf
    public PdfPTable tabla;//Declaracion tabla pdf
    public static final String AUTOR_PDF = "ErikTheGod";//constante del autor del pdf
    public static final String EXTENSION_PDF = ".pdf";//constante extension pdf
    public static final String EXTENSION_HTML = ".html";//constante extension html

    /**
     * Metodo que genera un pdf
     *
     * @param nombreArchivo nombre del archivo pdf
     * @throws FileNotFoundException
     * @throws DocumentException
     */
    public void generarPDF(File nombreArchivo) throws FileNotFoundException, DocumentException {
        documento = new Document();
        archivo = new FileOutputStream(nombreArchivo + EXTENSION_PDF);
        PdfWriter.getInstance(documento, archivo);
        documento.open();
        documento.addAuthor(AUTOR_PDF);
    }

    /**
     * Metodo que genera un html con tabla
     *
     * @param nombreArchivo nombre del archivo html
     * @throws FileNotFoundException
     */
    public void exportarHTML(File nombreArchivo) throws FileNotFoundException {
        fichero = new PrintWriter(nombreArchivo + EXTENSION_HTML);
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
