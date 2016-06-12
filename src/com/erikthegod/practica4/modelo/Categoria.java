package com.erikthegod.practica4.modelo;

import com.erikthegod.practica4.persistencia.GestorBBDD;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase que representa una categoria de productos y que accede a la BBDD para
 * recoger los nombres de las categorias que hay
 *
 * @author ErikTheGod
 */
public class Categoria {

    private String nombre;
    GestorBBDD gest = new GestorBBDD();//Instancia de un objeto de la clase GestorBBDD
    /**
     * Contructor de la clase
     *
     * @param nombreCategoria Nombre de la categoria
     */
    public Categoria(String nombreCategoria) {
        nombre = nombreCategoria;
    }
    /**
     * Contructor vacio
     */
    public Categoria() {
    }

    public String getNombre() {
        return nombre;
    }
}
