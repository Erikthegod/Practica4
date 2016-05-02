/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erikthegod.practica4;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author dam
 */
public class Producto {

    String nombre;
    Integer precio;
    String categoria;
    GestorBBDD gest = new GestorBBDD();
    Producto pro;
    ArrayList<Producto> productos = new ArrayList();

    public Producto(String nombre, Integer precio, String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    public Producto() {
    }

    public Producto recogerPrecio(String producto) {
        try {
            gest.conectar();
            gest.sql = "SELECT * from producto where nombrePro='" + producto + "'";
            gest.rs = gest.stmt.executeQuery(gest.sql);
            while (gest.rs.next()) {
                pro = new Producto(gest.rs.getString("nombrePro"), gest.rs.getInt("precio"), gest.rs.getString("nombre_cat"));
                productos.add(pro);
            }
            gest.c.close();
        } catch (SQLException ex) {
            System.err.println("ERROR DE SQL EXCEPTION");
        }
        return pro;
    }

    public void recogerProductos(String categoria) {
        try {
            gest.conectar();
            gest.sql = "SELECT * from producto where nombre_cat='" + categoria + "'";
            gest.rs = gest.stmt.executeQuery(gest.sql);
            while (gest.rs.next()) {
                pro = new Producto(gest.rs.getString("nombrePro"), gest.rs.getInt("precio"), gest.rs.getString("nombre_cat"));
                productos.add(pro);
            }
            gest.c.close();
        } catch (SQLException ex) {
            System.err.println("ERROR DE SQL EXCEPTION");
        }
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
