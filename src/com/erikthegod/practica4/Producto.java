/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erikthegod.practica4;

/**
 *
 * @author dam
 */
public class Producto {
    String nombre;
    Integer precio;
    String categoria;

    public Producto(String nombre, Integer precio, String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
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
