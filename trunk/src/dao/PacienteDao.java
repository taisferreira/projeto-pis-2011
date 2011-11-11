/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Paciente;

/**
 *
 * @author Fabricio
 */
public class PacienteDao {
    
    private static final String SQL_CREATE_PACIENTE = 
		"insert into paciente (CPF, NOME, ENDERECO, TELEFONE)"
              + " values((?),(?),(?),(?))";

    private Conexao conexao;
    
    public PacienteDao(Conexao con) {
        this.conexao = con;
    }
    
    public void create(Paciente paciente) throws ClassNotFoundException, DAOException{
        Connection c = null;
        PreparedStatement ps = null;

        boolean transactionState = false;
        try {
            c = conexao.getCon();
            transactionState = c.getAutoCommit();

            c.setAutoCommit(false);

            ps = c.prepareStatement(SQL_CREATE_PACIENTE);
            ps.setString(1, paciente.getCpfPaciente());
            ps.setString(2, paciente.getNomePaciente());
            ps.setString(3, paciente.getEndPaciente());
            ps.setString(4, paciente.getTelPaciente());

            int result = ps.executeUpdate();
            if (result != 1) {
                throw new DAOException("PacienteDAO: insert failed");
            }
            c.commit();
        } catch (SQLException sqlx) {
            try {
                c.rollback();
            } catch (SQLException e) {
                throw new DAOException(e.getMessage());
            }
            throw new DAOException(sqlx.getMessage());
        } finally {
            try {
                c.setAutoCommit(transactionState);
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                throw new DAOException(e.getMessage());
            }
        }
    }
    
}
