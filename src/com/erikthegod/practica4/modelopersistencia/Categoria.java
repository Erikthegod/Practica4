package com.erikthegod.practica4.modelopersistencia;

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
    public static Categoria cat;//Declaracion de un objeto Categoria,al ser static este objeto es igual en todas las clases
    public static ArrayList<Categoria> categorias = new ArrayList();//Declaracion de un array de objetos Categoria,al ser static este array es igual en todas las clases

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

    /**
     * Metodo que recoge de la BBDD los nombres de las categorias y se los va a
     * asignando a objetos Categoria que se van guardando en un array
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void recogerCategorias() throws ClassNotFoundException, SQLException {
        gest.conectar();//Conecta con la BBDD
        gest.sql = "SELECT * from categoria";//Realiza un select en la base de datos cogiendo los datos de la tabla categoria
        gest.rs = gest.stmt.executeQuery(gest.sql);
        while (gest.rs.next()) {//Recorre los resultados obtenidos por el select
            cat = new Categoria(gest.rs.getString("nombre"));//Instancia de un objeto de la clase categoria al que se le pasa el nombre recibido de la BBDD
            categorias.add(cat);//Se le agrega al array de categorias la categoria instanciada
        }
        gest.c.close();//Cierre de conexion
    }

    public String getNombre() {
        return nombre;
    }
}
