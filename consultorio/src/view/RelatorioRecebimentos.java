/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RelatorioRecebimentos.java
 *
 * Created on 24/11/2011, 11:19:40
 */
package view;

import control.Controler;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Pagamento;

/**
 *
 * @author Junior
 */
public class RelatorioRecebimentos extends javax.swing.JFrame {

    Controler controler;
    ArrayList<Pagamento> recebimentos;

    /** Creates new form RelatorioRecebimentos */
    public RelatorioRecebimentos() {
        initComponents();
    }

    public RelatorioRecebimentos(Controler controler) {
        this.controler = controler;
        initComponents();
        preencheTabela(null, 1);
    }
    
    void preencheTabela(String filtro, int status) {
        if (status == 1) {
            recebimentos = controler.buscaRecebimento(null, status);
        }
        if (status == 2) {
            recebimentos = controler.buscaRecebimento(filtro, status);
        }
        if (status == 3) {
            recebimentos = controler.buscaRecebimento(filtro, status);
        }
        if (status == 4) {
            recebimentos = controler.buscaRecebimento(null, status);
        }
        if (status == 5) {
            recebimentos = controler.buscaRecebimento(null, status);
        }
        
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        //REMOVE TODAS AS LINHAS DA TABELA QUE FOI RESULTADO DA PESQUISA ANTERIOR
        int nrLinhas = dtm.getRowCount();
        for (int i = 0; i < nrLinhas; i++) {
            dtm.removeRow(0);
        }
        for (int i = 0; i < recebimentos.size(); i++) {
            dtm.addRow(new Object[]{recebimentos.get(i).getCodigoConsulta(), recebimentos.get(i).getIdConv().getNome(), recebimentos.get(i).getValor(), recebimentos.get(i).getVencimento2()});
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        filtroPesquisa = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        pesquisar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Parâmetro de Pesquisa"));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos", "Cód. Consulta", "Convênio", "Recebido", "À receber" }));

        pesquisar.setText("Pesquisar");
        pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesquisarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(filtroPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox1, 0, 113, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pesquisar)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filtroPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pesquisar)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cód. Consulta", "Convênio", "Valor", "Vencimento"
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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesquisarActionPerformed
        if (jComboBox1.getSelectedItem().toString().equals("Todos")) {
            preencheTabela(null, 1);
        } else if (jComboBox1.getSelectedItem().toString().equals("Cód. Consulta")) { 
            try{
                    Integer.parseInt(filtroPesquisa.getText());
                    preencheTabela(filtroPesquisa.getText(), 2);

            }catch(Exception err) {
                javax.swing.JOptionPane.showMessageDialog(null, "Cód. Consulta inválido!\nDigite um número!", "Erro!", JOptionPane.ERROR_MESSAGE, null);
            }
        } else if (jComboBox1.getSelectedItem().toString().equals("Convênio")) {
            preencheTabela(filtroPesquisa.getText(), 3);
        } else if (jComboBox1.getSelectedItem().toString().equals("Recebido")) {
            preencheTabela(null, 4);
        } else if (jComboBox1.getSelectedItem().toString().equals("À receber")) {
            preencheTabela(null, 5);
        }
        filtroPesquisa.setText("");
    }//GEN-LAST:event_pesquisarActionPerformed

  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField filtroPesquisa;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton pesquisar;
    // End of variables declaration//GEN-END:variables
}
