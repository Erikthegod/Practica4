package com.erikthegod.practica4.modelo;

import java.util.ArrayList;

/**
 * Clase que representa una cesta , va guardando los productos de un pedido y
 * los va insertando en la BBDD , tambien crea ficheros html y pdf con los
 * pedidos de la BBDD
 *
 * @author ErikTheGod
 */
public class Cesta {

    private ArrayList<Producto> productosRecogidos = new ArrayList();
    private ArrayList<Integer> idPedido = new ArrayList();
    private int precioFinal;
    public static final String FUENTE_PDF = "arial";//Constante que contiene la fuente del pdf
    public static final String SIMBOLO_MONEDA_HTML = "&euro;";//Constante que contiene la moneda usada
    public static final String SIMBOLO_MONEDA_PDF = "€";//Constante que contiene la moneda usada

    /**
     *
     * @return Retorna un array de productos recogidos
     */
    public ArrayList<Producto> getProductosRecogidos() {
        return productosRecogidos;
    }

    public ArrayList<Integer> getIdPedido() {
        return idPedido;
    }

    public void setPrecioFinal(int precioFinal) {
        this.precioFinal = precioFinal;
    }

    public int getPrecioFinal() {
        return precioFinal;
    }

}
