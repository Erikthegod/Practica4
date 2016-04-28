/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erikthegod.practica4;

import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dam
 */
public class JPVentana extends javax.swing.JPanel {

    Vector vNombres;
    GestorBBDD gest = new GestorBBDD();
    Cesta cest = new Cesta();
    String categoria;
    String producto;
    /**
     * Creates new form JPVentana
     */
    public JPVentana() {
        initComponents();
        vNombres = new Vector();
        vNombres.add("Categoria");
        vNombres.add("Producto");
        vNombres.add("Precio");
        DefaultTableModel dtm = new DefaultTableModel(vNombres, 0);
        jtProductos.setModel(dtm);
        gest.recogerCategorias();
        for (int i = 0; i < gest.categorias.size(); i++) {
            jcbCategoria.addItem(gest.categorias.get(i).getNombre());
        }
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

        jbNuevo.setText("nuevo");
        jbNuevo.setFocusable(false);
        jbNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jtbMenu.add(jbNuevo);

        jbGuardar.setText("guardar");
        jbGuardar.setFocusable(false);
        jbGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jtbMenu.add(jbGuardar);

        jbGenerar.setText("generar");
        jbGenerar.setFocusable(false);
        jbGenerar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbGenerar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jtbMenu.add(jbGenerar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlCategoria)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jcbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlProducto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jbAniadir, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jtbMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
        jcbProducto.removeAllItems();
        gest.productos.clear();
        gest.recogerProductos((String) jcbCategoria.getSelectedItem());
        for (int i = 0; i < gest.productos.size(); i++) {
            jcbProducto.addItem(gest.productos.get(i).getNombre());
        }
    }//GEN-LAST:event_jcbCategoriaActionPerformed

    private void jbAniadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAniadirActionPerformed
        DefaultTableModel dtm = new DefaultTableModel(vNombres, 0);
        jtProductos.setModel(dtm);
        producto = (String) jcbProducto.getSelectedItem();
        cest.productos.add(gest.recogerPrecio(producto));
        for (int i = 0; i < cest.productos.size(); i++) {
            dtm.setRowCount(dtm.getRowCount() + 1);
            jtProductos.setValueAt(cest.productos.get(i).getCategoria(), i, 0);
            jtProductos.setValueAt(cest.productos.get(i).getNombre(), i, 1);
            jtProductos.setValueAt(cest.productos.get(i).getPrecio(), i, 2);
        }
    }//GEN-LAST:event_jbAniadirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbAniadir;
    private javax.swing.JButton jbGenerar;
    private javax.swing.JButton jbGuardar;
    private javax.swing.JButton jbNuevo;
    private javax.swing.JComboBox<String> jcbCategoria;
    private javax.swing.JComboBox<String> jcbProducto;
    private javax.swing.JLabel jlCategoria;
    private javax.swing.JLabel jlProducto;
    private javax.swing.JTable jtProductos;
    private javax.swing.JToolBar jtbMenu;
    // End of variables declaration//GEN-END:variables
}
