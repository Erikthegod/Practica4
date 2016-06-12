package com.erikthegod.practica4.modelo;

import com.erikthegod.practica4.persistencia.GestorBBDD;
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
