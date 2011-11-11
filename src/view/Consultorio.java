/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Consultorio.java
 *
 * Created on 21/09/2011, 09:18:53
 */
package view;

import dao.Conexao;
import model.Usuario;

/**
 *
 * @author Fabricio
 */
public class Consultorio extends javax.swing.JFrame {
    private Usuario user;
    private Conexao con;

    /** Creates new form Consultorio */
    public Consultorio(Usuario u, Conexao c) {
        user = u;
        con = c;
        initComponents();
    }

    public Conexao getCon() {
        return con;
    }

    public void setCon(Conexao con) {
        this.con = con;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
    
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mbPrincipal = new javax.swing.JMenuBar();
        mCadastrar = new javax.swing.JMenu();
        miCadastroMedico = new javax.swing.JMenuItem();
        miCadastroCliente = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        miCadastroFuncao = new javax.swing.JMenuItem();
        miCadastroFuncionario = new javax.swing.JMenuItem();
        mAtualizar = new javax.swing.JMenu();
        miAtualizarUsuario = new javax.swing.JMenuItem();
        miAtualizarPaciente = new javax.swing.JMenuItem();
        mExcluir = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Consultorio");
        setName("consultorio"); // NOI18N

        mCadastrar.setText("Cadastrar");

        miCadastroMedico.setText("Médico");
        miCadastroMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miCadastroMedicoActionPerformed(evt);
            }
        });
        mCadastrar.add(miCadastroMedico);

        miCadastroCliente.setText("Paciente");
        miCadastroCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miCadastroClienteActionPerformed(evt);
            }
        });
        mCadastrar.add(miCadastroCliente);

        jMenuItem8.setText("Despesa");
        mCadastrar.add(jMenuItem8);

        miCadastroFuncao.setText("Função");
        miCadastroFuncao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miCadastroFuncaoActionPerformed(evt);
            }
        });
        mCadastrar.add(miCadastroFuncao);

        miCadastroFuncionario.setText("Funcionário");
        miCadastroFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miCadastroFuncionarioActionPerformed(evt);
            }
        });
        mCadastrar.add(miCadastroFuncionario);

        mbPrincipal.add(mCadastrar);

        mAtualizar.setText("Atualizar");

        miAtualizarUsuario.setText("Usuário");
        mAtualizar.add(miAtualizarUsuario);

        miAtualizarPaciente.setText("Paciente");
        mAtualizar.add(miAtualizarPaciente);

        mbPrincipal.add(mAtualizar);

        mExcluir.setText("Excluir");

        jMenuItem1.setText("Usuário");
        mExcluir.add(jMenuItem1);

        jMenuItem2.setText("Paciente");
        mExcluir.add(jMenuItem2);

        mbPrincipal.add(mExcluir);

        jMenu1.setText("Consulta");

        jMenuItem3.setText("Marcar");
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText("Alterar");
        jMenu1.add(jMenuItem4);

        jMenuItem5.setText("Desmarcar");
        jMenu1.add(jMenuItem5);

        mbPrincipal.add(jMenu1);

        jMenu2.setText("Relatórios");

        jMenuItem6.setText("Recebimentos");
        jMenu2.add(jMenuItem6);

        jMenuItem7.setText("Despesas");
        jMenu2.add(jMenuItem7);

        mbPrincipal.add(jMenu2);

        setJMenuBar(mbPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void miCadastroMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miCadastroMedicoActionPerformed
    java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new CadastroMedico(con).setVisible(true);
            }
        });
}//GEN-LAST:event_miCadastroMedicoActionPerformed

private void miCadastroFuncaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miCadastroFuncaoActionPerformed
    java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new CadastrarFuncao(con).setVisible(true);
            }
        });
}//GEN-LAST:event_miCadastroFuncaoActionPerformed

private void miCadastroFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miCadastroFuncionarioActionPerformed
    java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new CadastroFuncionario(con).setVisible(true);
            }
        });
}//GEN-LAST:event_miCadastroFuncionarioActionPerformed

private void miCadastroClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miCadastroClienteActionPerformed
    java.awt.EventQueue.invokeLater(new Runnable() {

        public void run() {
            new CadastrarPaciente(con).setVisible(true);
        }
    });
}//GEN-LAST:event_miCadastroClienteActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenu mAtualizar;
    private javax.swing.JMenu mCadastrar;
    private javax.swing.JMenu mExcluir;
    private javax.swing.JMenuBar mbPrincipal;
    private javax.swing.JMenuItem miAtualizarPaciente;
    private javax.swing.JMenuItem miAtualizarUsuario;
    private javax.swing.JMenuItem miCadastroCliente;
    private javax.swing.JMenuItem miCadastroFuncao;
    private javax.swing.JMenuItem miCadastroFuncionario;
    private javax.swing.JMenuItem miCadastroMedico;
    // End of variables declaration//GEN-END:variables
}
