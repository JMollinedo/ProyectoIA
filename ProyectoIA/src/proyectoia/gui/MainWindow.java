/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoia.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import proyectoia.Naive;
import proyectoia.Pelicula;
import proyectoia.RNW;

/**
 *
 * @author jmoll
 */
public class MainWindow extends javax.swing.JFrame {

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
        yes = new ArrayList();
    }

    private Naive modelo;
    private List<Pelicula> recomendacion;
    private List<Integer> yes;
    
    private void ActualizarLista(boolean warm){
        DefaultTableModel model = (DefaultTableModel) jtRecom.getModel();
        while(model.getRowCount() > 0) model.removeRow(0);
        if(warm){
            List<Pelicula> copia = new ArrayList();
            recomendacion.forEach(r -> copia.add(r.copy()));
            for(int i = 0; i < copia.size(); i++){
                Pelicula p = copia.get(i);
                if(!modelo.inYesInput(p)){
                    if(yes.contains(i)){
                        p.setValorizacion(Pelicula.YES);
                        modelo.addYesInput(p);
                    }
                    else p.setValorizacion(Pelicula.NO);
                    modelo.addMuestra(p);
                }
            }
            yes.clear();
        }
        recomendacion = modelo.top50();
        recomendacion.forEach(p -> model.addRow(p.inLine()));
        jtRecom.setModel(model);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBcargar = new javax.swing.JButton();
        jBAbrir = new javax.swing.JButton();
        jBbuscar = new javax.swing.JButton();
        jbActualizar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtRecom = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1200, 800));

        jBcargar.setText("Cargar Archivo");
        jBcargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBcargarActionPerformed(evt);
            }
        });

        jBAbrir.setText("Abrir");
        jBAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAbrirActionPerformed(evt);
            }
        });

        jBbuscar.setText("Buscar");
        jBbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBbuscarActionPerformed(evt);
            }
        });

        jbActualizar.setText("Actualizar");
        jbActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbActualizarActionPerformed(evt);
            }
        });

        jtRecom.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pelicula", "Generos", "Argumento", "Director", "Actores", "Año", "Clasificación", "Idioma", "País", "IMDB Score", "Color"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtRecom.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jtRecom);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jBcargar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBbuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBAbrir)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBcargar)
                    .addComponent(jBAbrir)
                    .addComponent(jBbuscar)
                    .addComponent(jbActualizar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBcargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBcargarActionPerformed
        JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int res = fc.showOpenDialog(null);
        if(res == JFileChooser.APPROVE_OPTION){
            String path = fc.getSelectedFile().getPath();
            try{
                List<Pelicula> pels = RNW.ReadFile(path);
                modelo = new Naive(pels);
                ActualizarLista(false);
            }catch (IOException | NumberFormatException exc){
                 JOptionPane.showMessageDialog(rootPane, "Error al leer archivo " + exc.getMessage());
            }
        }
    }//GEN-LAST:event_jBcargarActionPerformed

    private void jbActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbActualizarActionPerformed
        // TODO add your handling code here:
        ActualizarLista(true);
    }//GEN-LAST:event_jbActualizarActionPerformed

    private void jBAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAbrirActionPerformed
        // TODO add your handling code here:
        int index = jtRecom.getSelectedRow();
        new VerPelicula(recomendacion.get(index)).setVisible(true);
        yes.add(index);
    }//GEN-LAST:event_jBAbrirActionPerformed

    private void jBbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBbuscarActionPerformed
        // TODO add your handling code here:
        new SearchWindow(modelo).setVisible(true);
    }//GEN-LAST:event_jBbuscarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBAbrir;
    private javax.swing.JButton jBbuscar;
    private javax.swing.JButton jBcargar;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbActualizar;
    private javax.swing.JTable jtRecom;
    // End of variables declaration//GEN-END:variables
}
