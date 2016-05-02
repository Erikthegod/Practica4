/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erikthegod.practica4;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.itextpdf.text.pdf.PdfPTable;

/**
 *
 * @author dam
 */
public class GestorBBDD {

    Connection c = null;//Conexión
    Statement stmt = null;//Sentencia
    String sql = null;//Cadena con la sentencia sql
    ResultSet rs = null;//Conjunto de resultados

    public void conectar() {
        try {
            Class.forName("org.sqlite.JDBC");//Carga del driver
            c = DriverManager.getConnection("jdbc:sqlite:E:/Grado/Programacion/Proyectos/Practica4/pedidos.db");
            stmt = c.createStatement();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GestorBBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GestorBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int comprobacionID() {
        int id = 0;
        try {
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
        } catch (SQLException ex) {
            System.err.println("ERROR DE SQL EXCEPTION");
        }
        return id;
    }

}
