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
    GestorBBDD gest = new GestorBBDD();//Instancia de un objeto de la clase GestorBBDD
    public static Producto pro;//Declaracion de un objeto Producto,al ser static este objeto es igual en todas las clases
    public static ArrayList<Producto> productos = new ArrayList();//Declaracion de un array de objetos Producto de la misma categoria,al ser static este array es igual en todas las clases

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
        gest.conectar();//Conecta con la BBDD
        gest.sql = "SELECT * from producto where nombrePro='" + producto + "'";//Realiza un select en la base de datos cogiendo los datos de la tabla producto donde el nombre del producto sea el que se le ha pasado al metodo
        gest.rs = gest.stmt.executeQuery(gest.sql);
        while (gest.rs.next()) {//Recorre los resultados obtenidos por el select
            pro = new Producto(gest.rs.getString("nombrePro"), gest.rs.getInt("precio"), gest.rs.getString("nombre_cat"));//Instancia de un objeto de la clase producto al que se le pasan los datos recogidos de la BBDD
        }
        gest.c.close();//Cierre de conexion
        return pro;//Retorna el producto instanciado
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
        gest.conectar();//Conecta con la BBDD
        gest.sql = "SELECT * from producto where nombre_cat='" + categoria + "'";//Realiza un select en la base de datos cogiendo los datos de la tabla producto donde la categoria del producto sea el que se le ha pasado al metodo
        gest.rs = gest.stmt.executeQuery(gest.sql);
        while (gest.rs.next()) {//Recorre los resultados obtenidos por el select
            pro = new Producto(gest.rs.getString("nombrePro"), gest.rs.getInt("precio"), gest.rs.getString("nombre_cat"));//Instancia de un objeto de la clase producto al que se le pasan los datos recogidos de la BBDD
            productos.add(pro);//Se agrega el objeto instanciado al array de productos , donde guardaremos los productos de la misma categoria
        }
        gest.c.close();//Cierre de conexion
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
