package com.erikthegod.practica4.persistencia;

import com.erikthegod.practica4.modelo.Cesta;
import static com.erikthegod.practica4.modelo.Cesta.FUENTE_PDF;
import static com.erikthegod.practica4.modelo.Cesta.SIMBOLO_MONEDA_HTML;
import static com.erikthegod.practica4.modelo.Cesta.SIMBOLO_MONEDA_PDF;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;

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
    GestorBBDD gest = new GestorBBDD();
    private Cesta cesta = new Cesta();
    public static final String AUTOR_PDF = "ErikTheGod";//constante del autor del pdf
    public static final String EXTENSION_PDF = ".pdf";//constante extension pdf
    public static final String EXTENSION_HTML = ".html";//constante extension html

    /**
     * Metodo que genera un pdf
     *
     * @param nombreArchivo nombre del archivo pdf
     * @throws FileNotFoundException Lanza una excepcion causada por error al
     * generar el archivo
     * @throws DocumentException Lanza una excepcion causada por error al
     * generar el documento
     */
    public void generarPDF(File nombreArchivo) throws FileNotFoundException, DocumentException {
        documento = new Document();
        archivo = new FileOutputStream(nombreArchivo + EXTENSION_PDF);
        PdfWriter.getInstance(documento, archivo);
        documento.open();
        documento.addAuthor(AUTOR_PDF);
    }

    /**
     * Metodo que genera e inserta los datos de todos los pedidos en una tabla
     * de un pdf
     *
     * @param nombreArchivo Nombre del archivo pdf
     * @throws DocumentException Lanza una excepcion causada por error al
     * generar el documento
     * @throws FileNotFoundException Lanza una excepcion causada por error al
     * generar el archivo
     */
    public void insertatDatosPDF(File nombreArchivo) throws DocumentException, FileNotFoundException, ClassNotFoundException, SQLException {
        cesta = new Cesta();
        cesta = gest.recuperarPedidos();
        generarPDF(nombreArchivo);//Llama a un metodo de la clase GestorArchivo que crea un documento pdf
        documento.add(new Paragraph("Lista Pedidos", FontFactory.getFont(FUENTE_PDF, // fuente
                22, // tamaño
                Font.ITALIC, // estilo
                BaseColor.GRAY)));
        documento.add(new Paragraph("\n"));
        tabla = new PdfPTable(4);//Creacion de tabla en pdf con numero de columnas
        tabla.addCell("Categoria");
        tabla.addCell("Producto");
        tabla.addCell("Precio");
        tabla.addCell("Id Pedido");
        for (int i = 0; i < cesta.getProductosRecogidos().size(); i++) {//Agregamos filas cada una con los productos recogidos y su id pedido correspondiente
            tabla.addCell(cesta.getProductosRecogidos().get(i).getCategoria());
            tabla.addCell(cesta.getProductosRecogidos().get(i).getNombre());
            tabla.addCell(cesta.getProductosRecogidos().get(i).getPrecio().toString() + SIMBOLO_MONEDA_PDF);
            tabla.addCell(cesta.getIdPedido().get(i).toString());
        }
        tabla.addCell("");
        tabla.addCell("");
        tabla.addCell(cesta.getPrecioFinal() + SIMBOLO_MONEDA_PDF + "");//Inserta el precio total de los productos
        tabla.addCell("Precio Total");
        documento.add(tabla);//Agrega la tabla creada al documento pdf
        documento.close();
    }

    /**
     * Metodo que genera un html con tabla
     *
     * @param nombreArchivo nombre del archivo html
     * @throws FileNotFoundException Lanza una excepcion causada por error al
     * generar el archivo
     */
    public void exportarHTML(File nombreArchivo) throws FileNotFoundException {
        fichero = new PrintWriter(nombreArchivo + EXTENSION_HTML);
        fichero.println("<!DOCTYPE html>");
        fichero.println("<html>");
        fichero.println("<head>");
        fichero.println("<title> ErikTheGod </title>");
        fichero.println("</head>");
        fichero.println("<meta charset='utf-8'>");
        fichero.println("<body bgcolor=\"blue\" text=\"white\">");
        fichero.println("<table border=\"5\" rules=\"all\">");
        fichero.println("<tr>");
        fichero.println("<td> Nombre </td>");
        fichero.println("<td> Categoria </td>");
        fichero.println("<td> Precio </td>");
        fichero.println("<td> Id Pedido </td>");
        fichero.println("</tr>");
    }

    /**
     * Metodo que genera e inserta los datos de todos los pedidos en una tabla
     * de un archivo html
     *
     * @param nombreArchivo Nombre del html generado
     * @throws FileNotFoundException Lanza una excepcion causada por error al
     * generar el archivo
     */
    public void introducirDatosHtml(File nombreArchivo) throws FileNotFoundException, ClassNotFoundException, SQLException {
        cesta = new Cesta();
        cesta = gest.recuperarPedidos();
        exportarHTML(nombreArchivo);//Llama a un metodo de GestorArchivos que crea un fichero html
        for (int i = 0; i < cesta.getProductosRecogidos().size(); i++) {//Insertamos los datos de cada producto en cada fila con su id pedido correspondiente
            fichero.println("<tr>");
            fichero.println("<td>" + cesta.getProductosRecogidos().get(i).getNombre() + "</td>");
            fichero.println("<td>" + cesta.getProductosRecogidos().get(i).getCategoria() + "</td>");
            fichero.println("<td align='right'>" + cesta.getProductosRecogidos().get(i).getPrecio() + SIMBOLO_MONEDA_HTML + "</td>");
            fichero.println("<td align='right'>" + cesta.getIdPedido().get(i) + "</td>");
            fichero.println("</tr>");
        }
        fichero.println("<tr>");
        fichero.println("<td> </td>");
        fichero.println("<td> </td>");
        fichero.println("<td>" + cesta.getPrecioFinal() + SIMBOLO_MONEDA_HTML + "</td>");//Inserta el precio total de los productos
        fichero.println("<td>Precio Total</td>");
        fichero.println("</tr>");
        fichero.close();
    }
}
