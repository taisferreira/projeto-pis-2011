/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * sss.java
 *
 * Created on 24/11/2011, 11:16:48
 */
package view;

import control.Controler;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Despesa;

/**
 *
 * @author Junior
 */
public class RelatorioDespesas extends javax.swing.JFrame {

    Controler controler;
    ArrayList<Despesa> despesas;

    /** Creates new form sss */
    public RelatorioDespesas(Controler controler) {
        this.controler = controler;
        initComponents();
        preencheTabela(null, 1); //preenche a tabela com todas as despesas
    }

    void preencheTabela(String filtro, int status) {
        if (status == 1) {
            despesas = controler.buscaDespesa(null, status);
        }
        if (status == 2) {
            despesas = controler.buscaDespesa(filtro, status);
        }
        if (status == 3) {
            despesas = controler.buscaDespesa(null, status);
        }
        if (status == 4) {
            despesas = controler.buscaDespesa(null, status);
        }
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        //REMOVE TODAS AS LINHAS DA TABELA QUE FOI RESULTADO DA PESQUISA ANTERIOR
        int nrLinhas = dtm.getRowCount();
        for (int i = 0; i < nrLinhas; i++) {
            dtm.removeRow(0);
        }
        for (int i = 0; i < despesas.size(); i++) {
            dtm.addRow(new Object[]{despesas.get(i).getDescricao(), despesas.get(i).getCpfUsuario(), despesas.get(i).getPago(), despesas.get(i).getValor()});
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        pesquisar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cpf = new javax.swing.JFormattedTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Parâmetro de Pesquisa"));
        jPanel1.setToolTipText("pa");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos", "CPF", "Pago", "À pagar" }));

        pesquisar.setText("Pesquisar");
        pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesquisarActionPerformed(evt);
            }
        });

        jLabel1.setText("CPF.:");

        try {
            cpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cpf, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addComponent(pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(pesquisar)
                    .addComponent(cpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descrição", "CPF", "Pago(S/N)", "Valor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesquisarActionPerformed
        if (jComboBox1.getSelectedItem().toString().equals("Todos")) {
            preencheTabela(null, 1);
        } else if (jComboBox1.getSelectedItem().toString().equals("CPF")) { 
            try{
              //  Integer.parseInt(cpf.getText().substring(13));
                String cpf2 = cpf.getText();
                String cpf3 = "";
                cpf3 = cpf3.concat(cpf2.substring(0, 3));
                cpf3 = cpf3.concat(cpf2.substring(4, 7));
                cpf3 = cpf3.concat(cpf2.substring(8, 11));
                cpf3 = cpf3.concat(cpf2.substring(12, 14));
                System.out.println("CPF: "+ cpf3);
                preencheTabela(cpf3, 2);
            }catch(Exception err) {
                javax.swing.JOptionPane.showMessageDialog(null, "CPF inválido!", "Erro!", JOptionPane.ERROR_MESSAGE, null);
            }
        } else if (jComboBox1.getSelectedItem().toString().equals("Pago")) {
            preencheTabela(null, 3);
        } else if (jComboBox1.getSelectedItem().toString().equals("À pagar")) {
            preencheTabela(null, 4);
        }
        cpf.setText("");
    }//GEN-LAST:event_pesquisarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField cpf;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton pesquisar;
    // End of variables declaration//GEN-END:variables
}
