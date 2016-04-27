/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erikthegod.practica4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author dam
 */
public class GestorBBDD {

    ArrayList<Categoria> categorias = new ArrayList();
    Categoria cat;
    ArrayList<Producto> productos = new ArrayList();
    Producto pro;

    public void recogerCategorias() {
        try {
            Connection c = null;//Conexión
            Statement stmt = null;//Sentencia
            String sql = null;//Cadena con la sentencia sql
            ResultSet rs = null;//Conjunto de resultados
            Class.forName("org.sqlite.JDBC");//Carga del driver
            c = DriverManager.getConnection("jdbc:sqlite:C:/practica4/practica4.db");
            stmt = c.createStatement();
            sql = "SELECT * from categoria";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                cat = new Categoria(rs.getString("nombre"));
                categorias.add(cat);
            }
            c.close();
        } catch (SQLException ex) {
            System.err.println("ERROR DE SQL EXCEPTION");
        } catch (ClassNotFoundException ex) {
            System.err.println("ERROR DE CLASS NOT FOUND");
        }
    }

    public void recogerProductos(String categoria) {
        try {
            Connection c = null;//Conexión
            Statement stmt = null;//Sentencia
            String sql = null;//Cadena con la sentencia sql
            ResultSet rs = null;//Conjunto de resultados
            Class.forName("org.sqlite.JDBC");//Carga del driver
            c = DriverManager.getConnection("jdbc:sqlite:C:/practica4/practica4.db");
            stmt = c.createStatement();
            sql = "SELECT * from producto where nombre_cat='" + categoria + "'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                pro = new Producto(rs.getString("producto"), rs.getInt("precio"),rs.getString("nombre_cat"));
                productos.add(pro);
            }
            c.close();
        } catch (SQLException ex) {
            System.err.println("ERROR DE SQL EXCEPTION");
        } catch (ClassNotFoundException ex) {
            System.err.println("ERROR DE CLASS NOT FOUND");
        }
    }

    public Producto recogerPrecio(String producto) {
        try {
            Connection c = null;//Conexión
            Statement stmt = null;//Sentencia
            String sql = null;//Cadena con la sentencia sql
            ResultSet rs = null;//Conjunto de resultados
            Class.forName("org.sqlite.JDBC");//Carga del driver
            c = DriverManager.getConnection("jdbc:sqlite:C:/practica4/practica4.db");
            stmt = c.createStatement();
            sql = "SELECT * from producto where nombre_cat='" + producto + "'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                pro = new Producto( rs.getString("producto"), rs.getInt("precio"),rs.getString("nombre_cat"));
            }
            c.close();
        } catch (SQLException ex) {
            System.err.println("ERROR DE SQL EXCEPTION");
        } catch (ClassNotFoundException ex) {
            System.err.println("ERROR DE CLASS NOT FOUND");
        }
        return pro;
    }
}
