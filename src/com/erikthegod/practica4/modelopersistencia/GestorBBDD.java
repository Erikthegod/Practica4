package com.erikthegod.practica4.modelopersistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
    public static final String CONEXION_CLASE = "jdbc:sqlite:../pedidos.db";//Constante conexion clase

    /**
     * Metodo que crea una conexion con la BBDD
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void conectar() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");//Carga del driver
        c = DriverManager.getConnection(CONEXION_CLASE);
        stmt = c.createStatement();
    }

    /**
     * Metodo que comprueba el id del ultimo pedido para no repetir
     *
     * @return Retorna el siguiente id libre
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public int comprobacionID() throws ClassNotFoundException, SQLException {
        int id = 0;
        conectar();
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
        c.close();
        return id;
    }
}
