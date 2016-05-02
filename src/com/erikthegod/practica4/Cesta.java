/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erikthegod.practica4;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dam
 */
public class Cesta {

    ArrayList<Producto> productos = new ArrayList();
    Producto pro;
    ArrayList<Integer> idPedido = new ArrayList();
    GestorBBDD gest = new GestorBBDD();

    public void llenarCesta(int id, String producto) {
        try {
            gest.conectar();
            gest.sql = "insert into pedido_producto values (" + id + ",'" + producto + "');";
            gest.stmt.executeUpdate(gest.sql);
            gest.c.close();
        } catch (SQLException ex) {
            System.err.println("ERROR DE SQL EXCEPTION");
        }
    }

    public void recuperarPedidos() {
        try {
            gest.conectar();
            productos.clear();
            gest.sql = "Select nombrePro , precio , nombre_cat , id_pedido\n"
                    + "from pedido_producto , producto\n"
                    + "where  nombrePro = producto_pedidos;";
            gest.rs = gest.stmt.executeQuery(gest.sql);
            while (gest.rs.next()) {
                pro = new Producto(gest.rs.getString("nombrePro"), gest.rs.getInt("precio"), gest.rs.getString("nombre_cat"));
                productos.add(pro);
                idPedido.add(gest.rs.getInt("id_pedido"));
            }
            gest.c.close();
        } catch (SQLException ex) {
            System.err.println("ERROR DE SQL EXCEPTION");
        }
    }

    public void generarPDF() {
        FileOutputStream archivo = null;
        try {
            archivo = new FileOutputStream("pedido.pdf");
            Document documento = new Document();
            PdfWriter.getInstance(documento, archivo);
            documento.open();
            documento.addAuthor("ErikTheGod");
            documento.add(new Paragraph("Lista Pedidos", FontFactory.getFont("arial", // fuente
                    22, // tamaño
                    Font.ITALIC, // estilo
                    BaseColor.GRAY)));
            documento.add(new Paragraph("\n"));
            PdfPTable tabla = new PdfPTable(4);
            tabla.addCell("Categoria");
            tabla.addCell("Producto");
            tabla.addCell("Precio");
            tabla.addCell("Id Pedido");
            for (int i = 0; i < productos.size(); i++) {
                tabla.addCell(productos.get(i).getCategoria());
                tabla.addCell(productos.get(i).getNombre());
                tabla.addCell(productos.get(i).getPrecio().toString());
                tabla.addCell(idPedido.get(i).toString());
            }
            documento.add(tabla);
            documento.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(GestorBBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(GestorBBDD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                archivo.close();
                productos.clear();
                idPedido.clear();
            } catch (IOException ex) {
                Logger.getLogger(GestorBBDD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
