package com.erikthegod.practica4.modelopersistencia;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author dam
 */
public class Cesta {

    private ArrayList<Producto> productosRecogidos = new ArrayList();
    Producto pro;
    private ArrayList<Integer> idPedido = new ArrayList();
    GestorBBDD gest = new GestorBBDD();
    GestorArchivos gestAr = new GestorArchivos();
    public static final String FUENTE_PDF = "arial";
    public static final String SIMBOLO_MONEDA = "€";

    public void llenarCesta(int id, String producto) throws ClassNotFoundException, SQLException {
        gest.conectar();
        gest.sql = "insert into pedido_producto values (" + id + ",'" + producto + "');";
        gest.stmt.executeUpdate(gest.sql);
        gest.c.close();
    }

    public void recuperarPedidos() throws ClassNotFoundException, SQLException {
        gest.conectar();
        productosRecogidos.clear();
        gest.sql = "Select nombrePro , precio , nombre_cat , id_pedido\n"
                + "from pedido_producto , producto\n"
                + "where  nombrePro = producto_pedidos;";
        gest.rs = gest.stmt.executeQuery(gest.sql);
        while (gest.rs.next()) {
            pro = new Producto(gest.rs.getString("nombrePro"), gest.rs.getInt("precio"), gest.rs.getString("nombre_cat"));
            productosRecogidos.add(pro);
            idPedido.add(gest.rs.getInt("id_pedido"));
        }
        gest.c.close();
    }

    public void insertatDatosPDF(File nombreArchivo) throws DocumentException, FileNotFoundException {
        gestAr.generarPDF(nombreArchivo);
        gestAr.documento.add(new Paragraph("Lista Pedidos", FontFactory.getFont(FUENTE_PDF, // fuente
                22, // tamaño
                Font.ITALIC, // estilo
                BaseColor.GRAY)));
        gestAr.documento.add(new Paragraph("\n"));
        gestAr.tabla = new PdfPTable(4);
        gestAr.tabla.addCell("Categoria");
        gestAr.tabla.addCell("Producto");
        gestAr.tabla.addCell("Precio");
        gestAr.tabla.addCell("Id Pedido");
        for (int i = 0; i < productosRecogidos.size(); i++) {
            gestAr.tabla.addCell(productosRecogidos.get(i).getCategoria());
            gestAr.tabla.addCell(productosRecogidos.get(i).getNombre());
            gestAr.tabla.addCell(productosRecogidos.get(i).getPrecio().toString() + SIMBOLO_MONEDA);
            gestAr.tabla.addCell(idPedido.get(i).toString());
        }
        gestAr.documento.add(gestAr.tabla);
        gestAr.documento.close();
    }

    public void introducirDatosHtml(File nombreArchivo) throws FileNotFoundException {
        gestAr.exportarHTML(nombreArchivo);
        for (int i = 0; i < productosRecogidos.size(); i++) {
            gestAr.fichero.println("<tr>");
            gestAr.fichero.println("<td>" + productosRecogidos.get(i).getNombre() + "</td>");
            gestAr.fichero.println("<td>" + productosRecogidos.get(i).getCategoria() + "</td>");
            gestAr.fichero.println("<td>" + productosRecogidos.get(i).getPrecio() + SIMBOLO_MONEDA + "</td>");
            gestAr.fichero.println("<td>" + idPedido.get(i) + "</td>");
            gestAr.fichero.println("</tr>");
        }
        gestAr.fichero.close();
    }

    public ArrayList<Producto> getProductosRecogidos() {
        return productosRecogidos;
    }

}
