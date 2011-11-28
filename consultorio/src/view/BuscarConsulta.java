/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RemarcarConsulta.java
 *
 * Created on 28/11/2011, 11:04:17
 */

package view;

import control.ConsultaControler;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author tais
 */
public class BuscarConsulta extends javax.swing.JFrame {
    int medicoSelecionado = 0;
    boolean carregou_medicos = false;
    private ArrayList<Object> medicos = ConsultaControler.getAllMedicos();

    /** Creates new form RemarcarConsulta */
    public BuscarConsulta() {
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

        labelPacienteCPF = new javax.swing.JLabel();
        jtf_cpf = new javax.swing.JTextField();
        labelMedico = new javax.swing.JLabel();
        lista_medicos = new javax.swing.JComboBox();
        mensagemBuscar = new javax.swing.JLabel();
        buscaConsulta = new javax.swing.JButton();
        labelAvisos = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Selecione uma consulta para remarcar");

        labelPacienteCPF.setText("CPF do Paciente");

        labelMedico.setText("Medico");

        lista_medicos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lista_medicosMousePressed(evt);
            }
        });
        lista_medicos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                lista_medicosItemStateChanged(evt);
            }
        });

        mensagemBuscar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mensagemBuscar.setText("  ");

        buscaConsulta.setText("Buscar");
        buscaConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscaConsultaActionPerformed(evt);
            }
        });

        labelAvisos.setText("  ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelAvisos, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelMedico)
                                    .addComponent(lista_medicos, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mensagemBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(labelPacienteCPF)
                            .addComponent(jtf_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(buscaConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelPacienteCPF)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtf_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mensagemBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelMedico)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lista_medicos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(labelAvisos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(buscaConsulta)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscaConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscaConsultaActionPerformed
        Object medico;
        //String cpf;
        if(ConsultaControler.pacienteEhValido(jtf_cpf.getText())){
            if(medicoSelecionado == 0){
                medico = null;
            }
            else{
                medico = medicos.get(medicoSelecionado-1);
            }

            ArrayList<Object> consultas =
                    ConsultaControler.selecionar_consulta_para_excluir(medico,jtf_cpf.getText());

            if(consultas.size() == 0){
                labelAvisos.setText("Nenhuma consulta foi encontrada");
            }
            else if(consultas.size() == 1){
                
                int n = JOptionPane.showConfirmDialog(this,
                        "\n"+consultas.get(0).toString(),
                        "Remarcar consulta?",JOptionPane.YES_NO_OPTION);
                if(n == JOptionPane.YES_OPTION){
                    //ConsultaControler.exclui_consulta(consultas.get(0));
                    ConsultaControler.remarcar_consulta(consultas.get(0));
                    labelAvisos.setText("Consulta remarcada!.");
                    this.dispose();
                }
                else labelAvisos.setText("Consulta inalterada.");
            }
            else{
                Object[] options = new Object[consultas.size()];
                int i;

                for(i=0; i< consultas.size(); i++){
                    options[i] = i+"-"+consultas.get(i).toString();
                }

                String input = (String) JOptionPane.showInputDialog(null, "Mais de uma consulta encontrada.",
                "Escolha a consulta a ser remarcada", JOptionPane.QUESTION_MESSAGE, null,
                 options, options[0]);

               if(null != input && input.isEmpty() == false){
                    i=0; int in=0;
                    while(input.substring(i, i+1).equals("-")==false){
                        in = in*10 + Integer.parseInt(input.substring(i, i+1));
                        i++;
                    }

                    int n = JOptionPane.showConfirmDialog(this,
                            "\n"+consultas.get(in).toString()+
                            "\n",
                            "Remarcar consulta?",JOptionPane.YES_NO_OPTION);
                    if(n == JOptionPane.YES_OPTION){
                        ConsultaControler.remarcar_consulta(consultas.get(in));
                        labelAvisos.setText("Consulta remarcada!.");
                        this.dispose();
                    }
                    else labelAvisos.setText("Consulta inalterada.");
                    }
               }
        }
        else{
            if(jtf_cpf.getText().isEmpty()){
                labelAvisos.setText("Por favor, informe o CPF do paciente.");
            }
            else labelAvisos.setText("Paciente não encontrado.");
        }
    }//GEN-LAST:event_buscaConsultaActionPerformed

    private void lista_medicosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_lista_medicosItemStateChanged
        medicoSelecionado = lista_medicos.getSelectedIndex();
    }//GEN-LAST:event_lista_medicosItemStateChanged

    private void lista_medicosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lista_medicosMousePressed
        if(carregou_medicos == false){
            lista_medicos.removeAllItems();

            lista_medicos.addItem("Selecione o médico");
            for (int i = 0; i < medicos.size(); i++){
              lista_medicos.addItem(medicos.get(i).toString());
            }

            carregou_medicos = true;
        }
    }//GEN-LAST:event_lista_medicosMousePressed

    /**
    * @param args the command line arguments
    */
  /*  public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RemarcarConsulta().setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscaConsulta;
    private javax.swing.JTextField jtf_cpf;
    private javax.swing.JLabel labelAvisos;
    private javax.swing.JLabel labelMedico;
    private javax.swing.JLabel labelPacienteCPF;
    private javax.swing.JComboBox lista_medicos;
    private javax.swing.JLabel mensagemBuscar;
    // End of variables declaration//GEN-END:variables

}
