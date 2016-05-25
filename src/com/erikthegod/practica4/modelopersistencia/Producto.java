package com.erikthegod.practica4.modelopersistencia;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase que representa un producto y que accede a la BBDD para recoger los
 * datos de productos
 *
 * @author ErikTheGod
 */
public class Producto {

    private String nombre;
    private int precio;
    private String categoria;
    GestorBBDD gest = new GestorBBDD();// Instancia de un objeto de la clase GestorBBDD
    public static Producto pro;// Declaracion de un objeto Producto,al ser static este objeto es igual en todas las clases
    public static ArrayList<Producto> productos = new ArrayList();// Declaracion de un array de objetos Producto,al ser static este array es igual en todas las clases

    /**
     * Constructor de la clase
     *
     * @param nombre Nombre del producto
     * @param precio Precio del producto
     * @param categoria Categoria a la que pertenece el producto
     */
    public Producto(String nombre, Integer precio, String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    /**
     * Constructor vacio
     */
    public Producto() {
    }

    /**
     * Metodo que recoge los datos del producto de la BBDD y retorna un producto
     * con los datos obtenidos
     *
     * @param producto Nombre del producto que queremos recoger
     * @return Retorna un producto con datos de la BBDD
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Producto recogerPrecio(String producto) throws ClassNotFoundException, SQLException {
        gest.conectar();
        gest.sql = "SELECT * from producto where nombrePro='" + producto + "'";
        gest.rs = gest.stmt.executeQuery(gest.sql);
        while (gest.rs.next()) {
            pro = new Producto(gest.rs.getString("nombrePro"), gest.rs.getInt("precio"), gest.rs.getString("nombre_cat"));
        }
        gest.c.close();
        return pro;
    }

    /**
     * Metodo que agrega los productos de una categoria recibida a un array de
     * productos
     *
     * @param categoria Nombre de la categoria de la que queremos los objetos
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void recogerProductos(String categoria) throws ClassNotFoundException, SQLException {
        gest.conectar();
        gest.sql = "SELECT * from producto where nombre_cat='" + categoria + "'";
        gest.rs = gest.stmt.executeQuery(gest.sql);
        while (gest.rs.next()) {
            pro = new Producto(gest.rs.getString("nombrePro"), gest.rs.getInt("precio"), gest.rs.getString("nombre_cat"));
            productos.add(pro);
        }
        gest.c.close();
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public Integer getPrecio() {
        return precio;
    }

}
