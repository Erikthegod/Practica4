package com.erikthegod.practica4.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author dam
 */
public class GestorBBDD {

    public Connection c = null;//Conexión
    public Statement stmt = null;//Sentencia
    public String sql = null;//Cadena con la sentencia sql
    public ResultSet rs = null;//Conjunto de resultados

    public void conectar() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");//Carga del driver
        c = DriverManager.getConnection("jdbc:sqlite:E:/Proyectos/Practica4/pedidos.db");
        stmt = c.createStatement();
    }

    public int comprobacionID() throws ClassNotFoundException, SQLException {
        int id = 0;
        conectar();
        sql = "Select count(*) from pedidos;";
        rs = stmt.executeQuery(sql);
        if (rs.getInt("count(*)") == 0) {
            sql = "insert into pedidos values (0);";
            stmt.executeUpdate(sql);
        } else {
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
