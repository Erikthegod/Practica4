package com.erikthegod.practica4.persistencia;

import com.erikthegod.practica4.modelo.Categoria;
import com.erikthegod.practica4.modelo.Cesta;
import com.erikthegod.practica4.modelo.Producto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Clase que maneja la BBDD
 *
 * @author ErikTheGod
 */
public class GestorBBDD {

    public Connection c = null;//Conexión
    public Statement stmt = null;//Sentencia
    public String sql = null;//Cadena con la sentencia sql
    public ResultSet rs = null;//Conjunto de resultados
    public static final String CONEXION = "jdbc:sqlite:pedidos.db";//Constante conexion clase

    private ArrayList<Producto> productos;//Declaracion de un array de objetos Producto de la misma categoria,al ser static este array es igual en todas las clases
    private Producto pro;//Declaracion de un objeto Producto,al ser static este objeto es igual en todas las clases
    private Categoria cat;//Declaracion de un objeto Categoria,al ser static este objeto es igual en todas las clases
    private ArrayList<Categoria> categorias;//Declaracion de un array de objetos Categoria,al ser static este array es igual en todas las clases
    private Cesta cest = new Cesta();//Declaracion de un objeto Categoria,al ser static este objeto es igual en todas las clases
    /**
     * Metodo que crea una conexion con la BBDD
     *
     * @throws ClassNotFoundException Lanza una excepcion causada por la falta
     * del driver de la BBDD
     * @throws SQLException Lanza una excepcion causada por un error en la
     * conexion a la BBDD
     */
    public void conectar() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");//Carga del driver
        c = DriverManager.getConnection(CONEXION);
        stmt = c.createStatement();
    }

    /**
     * Metodo que comprueba el id del ultimo pedido para no repetir
     *
     * @return Retorna el siguiente id libre
     * @throws ClassNotFoundException Lanza una excepcion causada por la falta
     * del driver de la BBDD
     * @throws SQLException Lanza una excepcion causada por un error en la
     * conexion a la BBDD
     */
    public int comprobacionID() throws ClassNotFoundException, SQLException {
        int id = 0;
        sql = "Select count(*) from pedidos;";//Cuenta los pedidos que hay
        rs = stmt.executeQuery(sql);
        if (rs.getInt("count(*)") == 0) {//Si no hay ningun pedido inserta el primer id pedido
            sql = "insert into pedidos values (0);";
            stmt.executeUpdate(sql);
        } else {//Si ya hay pedidos busca el id mayor que hay para insertar uno mas y nunca repetir
            sql = "Select id_pedidos from pedidos where id_pedidos = (select max(id_pedidos) from pedidos);";
            rs = stmt.executeQuery(sql);
            id = rs.getInt("id_pedidos") + 1;
            sql = "insert into pedidos values (" + id + ");";
            stmt.executeUpdate(sql);
        }
        return id;
    }

    /**
     * Metodo que recoge de la BBDD los nombres de las categorias y se los va a
     * asignando a objetos Categoria que se van guardando en un array
     *
     * @return 
     * @throws ClassNotFoundException Lanza una excepcion causada por la falta
     * del driver de la BBDD
     * @throws SQLException Lanza una excepcion causada por un error en la
     * conexion a la BBDD
     */
    public ArrayList<Categoria> recogerCategorias() throws ClassNotFoundException, SQLException {
        conectar();//Conecta con la BBDD
        categorias = new ArrayList();
        sql = "SELECT * from categoria";//Realiza un select en la base de datos cogiendo los datos de la tabla categoria
        rs = stmt.executeQuery(sql);
        while (rs.next()) {//Recorre los resultados obtenidos por el select
            cat = new Categoria(rs.getString("nombre"));//Instancia de un objeto de la clase categoria al que se le pasa el nombre recibido de la BBDD
            categorias.add(cat);//Se le agrega al array de categorias la categoria instanciada
        }
        return categorias;
    }

    /**
     * Metodo que recoge los datos del producto de la BBDD y retorna un producto
     * con los datos obtenidos
     *
     * @param producto Nombre del producto que queremos recoger
     * @return Retorna un producto con datos de la BBDD
     * @throws ClassNotFoundException Lanza una excepcion causada por la falta
     * del driver de la BBDD
     * @throws SQLException Lanza una excepcion causada por un error en la
     * conexion a la BBDD
     */
    public Producto recogerPrecio(String producto) throws ClassNotFoundException, SQLException {
        sql = "SELECT * from producto where nombrePro='" + producto + "'";//Realiza un select en la base de datos cogiendo los datos de la tabla producto donde el nombre del producto sea el que se le ha pasado al metodo
        rs = stmt.executeQuery(sql);
        while (rs.next()) {//Recorre los resultados obtenidos por el select
            pro = new Producto(rs.getString("nombrePro"), rs.getInt("precio"), rs.getString("nombre_cat"));//Instancia de un objeto de la clase producto al que se le pasan los datos recogidos de la BBDD
        }
        return pro;//Retorna el producto instanciado
    }

    /**
     * Metodo que agrega los productos de una categoria recibida a un array de
     * productos
     *
     * @param categoria Nombre de la categoria de la que queremos los objetos
     * @return 
     * @throws ClassNotFoundException Lanza una excepcion causada por la falta
     * del driver de la BBDD
     * @throws SQLException Lanza una excepcion causada por un error en la
     * conexion a la BBDD
     */
    public ArrayList<Producto> recogerProductos(String categoria) throws ClassNotFoundException, SQLException {
        productos = new ArrayList();
        sql = "SELECT * from producto where nombre_cat='" + categoria + "'";//Realiza un select en la base de datos cogiendo los datos de la tabla producto donde la categoria del producto sea el que se le ha pasado al metodo
        rs = stmt.executeQuery(sql);
        while (rs.next()) {//Recorre los resultados obtenidos por el select
            pro = new Producto(rs.getString("nombrePro"), rs.getInt("precio"), rs.getString("nombre_cat"));//Instancia de un objeto de la clase producto al que se le pasan los datos recogidos de la BBDD
            productos.add(pro);//Se agrega el objeto instanciado al array de productos , donde guardaremos los productos de la misma categoria
        }
        return productos;
    }

    /**
     * Metodo que inserta en la BBDD los productos de la cesta
     *
     * @param id ID del pedido al que corresponde el producto
     * @param producto Nombre del producto a introducir
     * @throws ClassNotFoundException Lanza una excepcion causada por la falta
     * del driver de la BBDD
     * @throws SQLException Lanza una excepcion causada por un error en la
     * conexion a la BBDD
     */
    public void llenarCesta(int id, String producto) throws ClassNotFoundException, SQLException {
        sql = "insert into pedido_producto values (" + id + ",'" + producto + "');";//Insercion de los productos y el id del pedido en la BBDD
        stmt.executeUpdate(sql);
    }

    /**
     * Metodo que recoge todos los productos de todos los pedidos en un array de
     * productos recogidos y por cada producto un id en un array de integer
     *
     * @return 
     * @throws ClassNotFoundException Lanza una excepcion causada por la falta
     * del driver de la BBDD
     * @throws SQLException Lanza una excepcion causada por un error en la
     * conexion a la BBDD
     */
    public Cesta recuperarPedidos() throws ClassNotFoundException, SQLException {
        conectar();
        int precioFinal = 0;
        sql = "Select nombrePro , precio , nombre_cat , id_pedido\n"//Select que recoge los productos con su id de pedido correspondiente
                + "from pedido_producto , producto\n"
                + "where  nombrePro = producto_pedidos order by nombre_cat;";
        rs = stmt.executeQuery(sql);
        while (rs.next()) {//Recorre los resultados obtenidos por el select            
            pro = new Producto(rs.getString("nombrePro"), rs.getInt("precio"), rs.getString("nombre_cat"));//Instancia de un objeto de la clase producto al que se le pasan los datos recogidos de la BBDD
            cest.getProductosRecogidos().add(pro);//Agrega el producto instanciado a un array de productos recogidos 
            cest.getIdPedido().add(rs.getInt("id_pedido"));//Agrega el id del pedido de cada producto a un array de id's
            precioFinal = precioFinal + rs.getInt("precio");//Incrementa el precio final con el precio de cada producto recogido
        }
        cest.setPrecioFinal(precioFinal);
        return cest;
    }
    
    

}
