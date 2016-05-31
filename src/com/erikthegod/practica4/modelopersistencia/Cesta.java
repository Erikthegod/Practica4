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
 * Clase que representa una cesta , va guardando los productos de un pedido y
 * los va insertando en la BBDD , tambien crea ficheros html y pdf con los
 * pedidos de la BBDD
 *
 * @author ErikTheGod
 */
public class Cesta {

    private ArrayList<Producto> productosRecogidos = new ArrayList();
    private ArrayList<Integer> idPedido = new ArrayList();
    private int precioFinal;
    GestorBBDD gest = new GestorBBDD(); //Instancia de un objeto de la clase GestorBBDD
    GestorArchivos gestAr = new GestorArchivos(); //Instancia de un objeto de la clase GestorArchivos
    Producto pro;//Declaracion de un objeto de la clase Producto
    public static final String FUENTE_PDF = "arial";//Constante que contiene la fuente del pdf
    public static final String SIMBOLO_MONEDA = "€";//Constante que contiene la moneda usada

    /**
     * Metodo que inserta en la BBDD los productos de la cesta
     *
     * @param id ID del pedido al que corresponde el producto
     * @param producto Nombre del producto a introducir
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void llenarCesta(int id, String producto) throws ClassNotFoundException, SQLException {
        gest.conectar();//Conecta con la BBDD
        gest.sql = "insert into pedido_producto values (" + id + ",'" + producto + "');";//Insercion de los productos y el id del pedido en la BBDD
        gest.stmt.executeUpdate(gest.sql);
        gest.c.close();//Cierra conexion
    }

    /**
     * Metodo que recoge todos los productos de todos los pedidos en un array de
     * productos recogidos y por cada producto un id en un array de integer
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void recuperarPedidos() throws ClassNotFoundException, SQLException {
        gest.conectar();//Conecta con la BBDD
        productosRecogidos.clear();//Se vacia el array static
        precioFinal = 0;
        gest.sql = "Select nombrePro , precio , nombre_cat , id_pedido\n"//Select que recoge los productos con su id de pedido correspondiente
                + "from pedido_producto , producto\n"
                + "where  nombrePro = producto_pedidos order by nombre_cat;";
        gest.rs = gest.stmt.executeQuery(gest.sql);
        while (gest.rs.next()) {//Recorre los resultados obtenidos por el select
            pro = new Producto(gest.rs.getString("nombrePro"), gest.rs.getInt("precio"), gest.rs.getString("nombre_cat"));//Instancia de un objeto de la clase producto al que se le pasan los datos recogidos de la BBDD
            productosRecogidos.add(pro);//Agrega el producto instanciado a un array de productos recogidos 
            idPedido.add(gest.rs.getInt("id_pedido"));//Agrega el id del pedido de cada producto a un array de id's
            precioFinal = precioFinal + gest.rs.getInt("precio");//Incrementa el precio final con el precio de cada producto recogido
        }
        gest.c.close();//Cierra conexion
    }

    /**
     * Metodo que genera e inserta los datos de todos los pedidos en una tabla
     * de un pdf
     *
     * @param nombreArchivo Nombre del archivo pdf
     * @throws DocumentException
     * @throws FileNotFoundException
     */
    public void insertatDatosPDF(File nombreArchivo) throws DocumentException, FileNotFoundException {
        gestAr.generarPDF(nombreArchivo);//Llama a un metodo de la clase GestorArchivo que crea un documento pdf
        gestAr.documento.add(new Paragraph("Lista Pedidos", FontFactory.getFont(FUENTE_PDF, // fuente
                22, // tamaño
                Font.ITALIC, // estilo
                BaseColor.GRAY)));
        gestAr.documento.add(new Paragraph("\n"));
        gestAr.tabla = new PdfPTable(4);//Creacion de tabla en pdf con numero de columnas
        gestAr.tabla.addCell("Categoria");
        gestAr.tabla.addCell("Producto");
        gestAr.tabla.addCell("Precio");
        gestAr.tabla.addCell("Id Pedido");
        for (int i = 0; i < productosRecogidos.size(); i++) {//Agregamos filas cada una con los productos recogidos y su id pedido correspondiente
            gestAr.tabla.addCell(productosRecogidos.get(i).getCategoria());
            gestAr.tabla.addCell(productosRecogidos.get(i).getNombre());
            gestAr.tabla.addCell(productosRecogidos.get(i).getPrecio().toString() + SIMBOLO_MONEDA);
            gestAr.tabla.addCell(idPedido.get(i).toString());
        }
        gestAr.tabla.addCell("");
        gestAr.tabla.addCell("");
        gestAr.tabla.addCell(precioFinal + SIMBOLO_MONEDA + "");//Inserta el precio total de los productos
        gestAr.tabla.addCell("Precio Total");
        gestAr.documento.add(gestAr.tabla);//Agrega la tabla creada al documento pdf
        gestAr.documento.close();
    }

    /**
     * Metodo que genera e inserta los datos de todos los pedidos en una tabla
     * de un archivo html
     *
     * @param nombreArchivo Nombre del html generado
     * @throws FileNotFoundException
     */
    public void introducirDatosHtml(File nombreArchivo) throws FileNotFoundException {
        gestAr.exportarHTML(nombreArchivo);//Llama a un metodo de GestorArchivos que crea un fichero html
        for (int i = 0; i < productosRecogidos.size(); i++) {//Insertamos los datos de cada producto en cada fila con su id pedido correspondiente
            gestAr.fichero.println("<tr>");
            gestAr.fichero.println("<td>" + productosRecogidos.get(i).getNombre() + "</td>");
            gestAr.fichero.println("<td>" + productosRecogidos.get(i).getCategoria() + "</td>");
            gestAr.fichero.println("<td>" + productosRecogidos.get(i).getPrecio() + SIMBOLO_MONEDA + "</td>");
            gestAr.fichero.println("<td>" + idPedido.get(i) + "</td>");
            gestAr.fichero.println("</tr>");
        }
        gestAr.fichero.println("<tr>");
        gestAr.fichero.println("<td> </td>");
        gestAr.fichero.println("<td> </td>");
        gestAr.fichero.println("<td>" + precioFinal + SIMBOLO_MONEDA + "</td>");//Inserta el precio total de los productos
        gestAr.fichero.println("<td>Precio Total</td>");
        gestAr.fichero.println("</tr>");
        gestAr.fichero.close();
    }

    /**
     *
     * @return Retorna un array de productos recogidos
     */
    public ArrayList<Producto> getProductosRecogidos() {
        return productosRecogidos;
    }

}
