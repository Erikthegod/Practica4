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
public class Categoria {

    private String nombre;
    GestorBBDD gest = new GestorBBDD();
    Categoria cat;
    ArrayList<Categoria> categorias = new ArrayList();

    Categoria(String nombreCategoria) {
        nombre = nombreCategoria;
    }

    public Categoria() {
    }

    public void recogerCategorias() {
        try {
            gest.conectar();
            gest.sql = "SELECT * from categoria";
            gest.rs = gest.stmt.executeQuery(gest.sql);
            while (gest.rs.next()) {
                cat = new Categoria(gest.rs.getString("nombre"));
                categorias.add(cat);
            }
            gest.c.close();
        } catch (SQLException ex) {
            System.err.println("ERROR DE SQL EXCEPTION");
        }
    }

    public String getNombre() {
        return nombre;
    }
}
