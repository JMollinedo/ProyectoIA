/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoia;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jmoll
 */
public class SearchWindow extends javax.swing.JFrame {

    /**
     * Creates new form SearchWindow
     */
    public SearchWindow() {
        initComponents();
        yes = new ArrayList();
    }
    public SearchWindow(Naive naive){
        this();
        modelo = naive;
        start = true;
    }
    
    private Naive modelo;
    private List<Pelicula> resultados;
    private List<Integer> yes;
    private boolean start;
    
    private void ActualizarModelo(){
        List<Pelicula> copia = new ArrayList();
        resultados.forEach(r -> copia.add(r.copy()));
        List<Pelicula> si = new ArrayList();
        List<Pelicula> no = new ArrayList();
        for(int i = 0; i < copia.size(); i++){
            Pelicula p = copia.get(i);
            if(!modelo.inYesInput(p)){
                if(yes.contains(i)){
                    p.setValorizacion(Pelicula.YES);
                    modelo.addYesInput(p);
                    si.add(p);
                }else{
                    p.calcValorazacion(Pelicula.NO);
                    no.add(p);
                }
            }
        }
        if(si.size()<no.size()){
            Random rand = new Random();
            while(no.size()>si.size()){
                no.remove(rand.nextInt(no.size()));
            }
        }
        for(Pelicula p : si) modelo.addMuestra(p);
        for(Pelicula p : no) modelo.addMuestra(p);
        yes.clear();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCBcampo = new javax.swing.JComboBox<>();
        jTFvalor = new javax.swing.JTextField();
        jBbuscar = new javax.swing.JButton();
        jBabrir = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtResult = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1200, 800));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                alCerrar(evt);
            }
        });

        jCBcampo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "genres", "plot_keywords", "actors", "color", "language", "country", "content_rating", "decade", "score", "director" }));

        jBbuscar.setText("Buscar");
        jBbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBbuscarActionPerformed(evt);
            }
        });

        jBabrir.setText("Abrir");
        jBabrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBabrirActionPerformed(evt);
            }
        });

        jtResult.setModel(new javax.swing.table.DefaultTableModel(
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
        jtResult.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jtResult);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCBcampo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFvalor, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBabrir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBbuscar))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBcampo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFvalor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBbuscar)
                    .addComponent(jBabrir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBbuscarActionPerformed
        // TODO add your handling code here:
        if(!start) ActualizarModelo();
        DefaultTableModel model = (DefaultTableModel) jtResult.getModel();
        while(model.getRowCount() > 0) model.removeRow(0);
        String campo = jCBcampo.getSelectedItem().toString();
        String valor = jTFvalor.getText();
        resultados = modelo.buscar(campo, valor);
        resultados.forEach(p -> model.addRow(p.inLine()));
        jtResult.setModel(model);
        start = false;
    }//GEN-LAST:event_jBbuscarActionPerformed

    private void jBabrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBabrirActionPerformed
        // TODO add your handling code here:
        if(!start){
            int index = jtResult.getSelectedRow();
            yes.add(index);
            new VerPelicula(resultados.get(index)).setVisible(true);
        }
    }//GEN-LAST:event_jBabrirActionPerformed

    private void alCerrar(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_alCerrar
        // TODO add your handling code here:
        if(!start) ActualizarModelo();
    }//GEN-LAST:event_alCerrar

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBabrir;
    private javax.swing.JButton jBbuscar;
    private javax.swing.JComboBox<String> jCBcampo;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTFvalor;
    private javax.swing.JTable jtResult;
    // End of variables declaration//GEN-END:variables
}
