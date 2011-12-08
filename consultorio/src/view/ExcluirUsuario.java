/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ExcluirUsuario.java
 *
 * Created on 19/11/2011, 17:38:55
 */

package view;

import control.Controler;
import dao.Conexao;
import dao.DAOException;
import dao.UsuarioDao;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Usuario;
import util.Misc;

/**
 *
 * @author Lucas
 */
public class ExcluirUsuario extends javax.swing.JFrame {
    private Controler controler;
    /** Creates new form CadastrarPaciente */
    public ExcluirUsuario(Controler c) {
        this.controler = c;
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botaocancelar = new javax.swing.JButton();
        labelcpf = new javax.swing.JLabel();
        botaoexcluir = new javax.swing.JButton();
        jfCpf = new javax.swing.JFormattedTextField();
        tfNome = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        botaocancelar.setText("Cancelar");
        botaocancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaocancelarActionPerformed(evt);
            }
        });

        labelcpf.setText("CPF:");

        botaoexcluir.setText("Excluir");
        botaoexcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoexcluirActionPerformed(evt);
            }
        });

        try {
            jfCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jfCpf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jfCpfFocusLost(evt);
            }
        });

        tfNome.setEditable(false);

        jLabel1.setText("Nome:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelcpf)
                            .addComponent(jfCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(botaoexcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(botaocancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelcpf)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jfCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(5, 5, 5)
                .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoexcluir)
                    .addComponent(botaocancelar))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaocancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaocancelarActionPerformed
        this.dispose();
}//GEN-LAST:event_botaocancelarActionPerformed

    private void botaoexcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoexcluirActionPerformed
        controler.excluirUsuario(Misc.getDigitos(jfCpf.getText()));
        this.dispose();
}//GEN-LAST:event_botaoexcluirActionPerformed

private void jfCpfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jfCpfFocusLost
    Usuario p = controler.buscarUsuario(Misc.getDigitos(jfCpf.getText()));
        tfNome.setText(p.getUserName());
}//GEN-LAST:event_jfCpfFocusLost

    /**
    * @param args the command line arguments
    */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaocancelar;
    private javax.swing.JButton botaoexcluir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JFormattedTextField jfCpf;
    private javax.swing.JLabel labelcpf;
    private javax.swing.JTextField tfNome;
    // End of variables declaration//GEN-END:variables

}