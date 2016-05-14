package com.erikthegod.practica4.modelo;

import com.erikthegod.practica4.persistencia.GestorBBDD;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author dam
 */
public class Producto {

    private String nombre;
    private int precio;
    private String categoria;
    GestorBBDD gest = new GestorBBDD();
    public static Producto pro;
    public static ArrayList<Producto> productos = new ArrayList();

    public Producto(String nombre, Integer precio, String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    public Producto() {
    }

    public Producto recogerPrecio(String producto) throws ClassNotFoundException, SQLException {
        gest.conectar();
        gest.sql = "SELECT * from producto where nombrePro='" + producto + "'";
        gest.rs = gest.stmt.executeQuery(gest.sql);
        while (gest.rs.next()) {
            pro = new Producto(gest.rs.getString("nombrePro"), gest.rs.getInt("precio"), gest.rs.getString("nombre_cat"));
            productos.add(pro);
        }
        gest.c.close();
        return pro;
    }

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
