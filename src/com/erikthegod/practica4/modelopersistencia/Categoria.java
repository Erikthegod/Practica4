package com.erikthegod.practica4.modelopersistencia;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author dam
 */
public class Categoria {

    private String nombre;
    GestorBBDD gest = new GestorBBDD();
    public static Categoria cat;
    public static ArrayList<Categoria> categorias = new ArrayList();

    Categoria(String nombreCategoria) {
        nombre = nombreCategoria;
    }

    public Categoria() {
    }

    public void recogerCategorias() throws ClassNotFoundException, SQLException {
        gest.conectar();
        gest.sql = "SELECT * from categoria";
        gest.rs = gest.stmt.executeQuery(gest.sql);
        while (gest.rs.next()) {
            cat = new Categoria(gest.rs.getString("nombre"));
            categorias.add(cat);
        }
        gest.c.close();
    }

    public String getNombre() {
        return nombre;
    }
}
