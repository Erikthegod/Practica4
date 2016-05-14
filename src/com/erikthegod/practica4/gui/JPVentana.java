/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erikthegod.practica4.gui;

import com.erikthegod.practica4.modelo.Categoria;
import com.erikthegod.practica4.modelo.Cesta;
import com.erikthegod.practica4.persistencia.GestorBBDD;
import com.erikthegod.practica4.modelo.Producto;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dam
 */
public class JPVentana extends javax.swing.JPanel {

    Vector vNombres;
    GestorBBDD gest = new GestorBBDD();
    Cesta cest = new Cesta();
    Categoria cat = new Categoria();
    Producto pro = new Producto();
    String categoria;
    String producto;
    int id;
    DefaultTableModel dtm;

    /**
     * Creates new form JPVentana
     */
    public JPVentana() {
        initComponents();
        vNombres = new Vector();
        vNombres.add("Categoria");
        vNombres.add("Producto");
        vNombres.add("Precio");
        dtm = new DefaultTableModel(vNombres, 0);
        jtProductos.setModel(dtm);
        jbAniadir.setEnabled(false);
        jbGenerar.setEnabled(false);
        jbGuardar.setEnabled(false);
        jcbCategoria.setEnabled(false);
        jcbProducto.setEnabled(false);
        jbPDF.setEnabled(false);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlCategoria = new javax.swing.JLabel();
        jcbCategoria = new javax.swing.JComboBox<>();
        jlProducto = new javax.swing.JLabel();
        jcbProducto = new javax.swing.JComboBox<>();
        jbAniadir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtProductos = new javax.swing.JTable();
        jtbMenu = new javax.swing.JToolBar();
        jbNuevo = new javax.swing.JButton();
        jbGuardar = new javax.swing.JButton();
        jbGenerar = new javax.swing.JButton();
        jbPDF = new javax.swing.JButton();

        jlCategoria.setText("Categoria");

        jcbCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbCategoriaActionPerformed(evt);
            }
        });

        jlProducto.setText("Producto");

        jbAniadir.setText("Añadir producto a la cesta");
        jbAniadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAniadirActionPerformed(evt);
            }
        });

        jtProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jtProductos);

        jtbMenu.setRollover(true);

        jbNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/erikthegod/practica4/recursos/guardar.png"))); // NOI18N
        jbNuevo.setFocusable(false);
        jbNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbNuevoActionPerformed(evt);
            }
        });
        jtbMenu.add(jbNuevo);

        jbGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/erikthegod/practica4/recursos/guardar1.png"))); // NOI18N
        jbGuardar.setFocusable(false);
        jbGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbGuardarActionPerformed(evt);
            }
        });
        jtbMenu.add(jbGuardar);

        jbGenerar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/erikthegod/practica4/recursos/settings.png"))); // NOI18N
        jbGenerar.setFocusable(false);
        jbGenerar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbGenerar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbGenerarActionPerformed(evt);
            }
        });
        jtbMenu.add(jbGenerar);

        jbPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/erikthegod/practica4/recursos/documentacion-pdf_318-33165.png"))); // NOI18N
        jbPDF.setFocusable(false);
        jbPDF.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbPDF.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPDFActionPerformed(evt);
            }
        });
        jtbMenu.add(jbPDF);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(83, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jtbMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jlCategoria)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jcbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlProducto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbAniadir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jtbMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlCategoria)
                    .addComponent(jcbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlProducto)
                    .addComponent(jcbProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbAniadir, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jcbCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbCategoriaActionPerformed
        try {
            jcbProducto.removeAllItems();
            Producto.productos.clear();
            pro.recogerProductos((String) jcbCategoria.getSelectedItem());
            for (int i = 0; i < Producto.productos.size(); i++) {
                jcbProducto.addItem(Producto.productos.get(i).getNombre());
            }
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos", "Base de Datos", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de SQL", "Error SQL", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jcbCategoriaActionPerformed

    private void jbAniadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAniadirActionPerformed
        try {
            dtm = new DefaultTableModel(vNombres, 0);
            jtProductos.setModel(dtm);
            producto = (String) jcbProducto.getSelectedItem();
            cest.getProductosRecogidos().add(pro.recogerPrecio(producto));
            for (int i = 0; i < cest.getProductosRecogidos().size(); i++) {
                dtm.setRowCount(dtm.getRowCount() + 1);
                jtProductos.setValueAt(cest.getProductosRecogidos().get(i).getCategoria(), i, 0);
                jtProductos.setValueAt(cest.getProductosRecogidos().get(i).getNombre(), i, 1);
                jtProductos.setValueAt(cest.getProductosRecogidos().get(i).getPrecio(), i, 2);
            }
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos", "Base de Datos", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de SQL", "Error SQL", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jbAniadirActionPerformed

    private void jbNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbNuevoActionPerformed

        int resp = JOptionPane.showConfirmDialog(null, "Desea crear un pedido nuevo,se borrara todo lo que no haya sido guardado", "Precaucion", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_OPTION);
        if (JOptionPane.YES_OPTION == resp) {
            try {
                jbAniadir.setEnabled(true);
                jbGenerar.setEnabled(true);
                jbGuardar.setEnabled(true);
                jcbCategoria.setEnabled(true);
                jcbProducto.setEnabled(true);
                jbPDF.setEnabled(true);
                Categoria.categorias.clear();
                jcbCategoria.removeAllItems();
                jcbProducto.removeAllItems();
                cest.getProductosRecogidos().clear();
                for (int i = 0; i < jtProductos.getRowCount(); i++) {
                    dtm.removeRow(i);
                    i -= 1;
                }
                cat.recogerCategorias();
                for (int i = 0; i < Categoria.categorias.size(); i++) {
                    jcbCategoria.addItem(Categoria.categorias.get(i).getNombre());
                }
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos", "Base de Datos", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error de SQL", "Error SQL", JOptionPane.ERROR_MESSAGE);
            }

        }


    }//GEN-LAST:event_jbNuevoActionPerformed

    private void jbGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbGuardarActionPerformed
        try {
            // TODO add your handling code here:
            id = gest.comprobacionID();
            for (int i = 0; i < cest.getProductosRecogidos().size(); i++) {
                cest.llenarCesta(id, cest.getProductosRecogidos().get(i).getNombre());
            }
            for (int i = 0; i < jtProductos.getRowCount(); i++) {
                dtm.removeRow(i);
                i -= 1;
            }
            cest.getProductosRecogidos().clear();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos", "Base de Datos", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de SQL", "Error SQL", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jbGuardarActionPerformed

    private void jbPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPDFActionPerformed
        try {
            // TODO add your handling code here:
            cest.recuperarPedidos();
            cest.insertatDatosPDF();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos", "Base de Datos", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de SQL", "Error SQL", JOptionPane.ERROR_MESSAGE);
        } catch (DocumentException | FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error al generar el documento PDF", "Error PDF", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jbPDFActionPerformed

    private void jbGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbGenerarActionPerformed
        try {
            // TODO add your handling code here:
            cest.recuperarPedidos();
            cest.introducirDatosHtml();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos", "Base de Datos", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de SQL", "Error SQL", JOptionPane.ERROR_MESSAGE);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error al generar el documento HTML", "Error HTML", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jbGenerarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbAniadir;
    private javax.swing.JButton jbGenerar;
    private javax.swing.JButton jbGuardar;
    private javax.swing.JButton jbNuevo;
    private javax.swing.JButton jbPDF;
    private javax.swing.JComboBox<String> jcbCategoria;
    private javax.swing.JComboBox<String> jcbProducto;
    private javax.swing.JLabel jlCategoria;
    private javax.swing.JLabel jlProducto;
    private javax.swing.JTable jtProductos;
    private javax.swing.JToolBar jtbMenu;
    // End of variables declaration//GEN-END:variables
}
