/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Paciente;
import java.sql.ResultSet;

/**
 *
 * @author Fabricio
 */
public class PacienteDao {
    
    private static final String SQL_CREATE_PACIENTE = 
		"insert into paciente (CPF, NOME, ENDERECO, TELEFONE)"
              + " values((?),(?),(?),(?))";

    private static final String SQL_DELETE_PACIENTE =
                "delete from paciente where CPF = (?)";
    
     private static final String SQL_FIND_PACIENTE =
                "select * from paciente where CPF = (?)";
     
     private static final String SQL_UPDATE_PACIENTE =
                "update paciente set NOME = (?), ENDERECO = (?), TELEFONE =(?) where CPF = (?)";


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

     public void delete(String cpf) throws ClassNotFoundException, DAOException{
        Connection c = null;
        PreparedStatement ps = null;

        boolean transactionState = false;
        try {
            c = conexao.getCon();
            transactionState = c.getAutoCommit();

            c.setAutoCommit(false);

            ps = c.prepareStatement(SQL_DELETE_PACIENTE);
            ps.setString(1, cpf);

            int result = ps.executeUpdate();
            if (result != 1) {
                throw new DAOException("PacienteDAO: delete failed");
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

      public static Paciente buscar(String cpf){
        Connection c;
        PreparedStatement ps;
        ResultSet rs;
        Paciente paciente = null;

        try {
            c = new Conexao().getCon();

            ps = c.prepareStatement(SQL_FIND_PACIENTE);
            ps.setString(1, cpf);

            rs = ps.executeQuery();

            if (rs.next()){
                paciente = new Paciente();
                paciente.setCpfPaciente(cpf);
                paciente.setNomePaciente(rs.getString("nome"));
                paciente.setEndPaciente(rs.getString("endereco"));
                paciente.setTelPaciente(rs.getString("telefone"));
            }

        } catch (Exception sqlx) {}

        return paciente;
     }
      public void update(Paciente paciente) throws ClassNotFoundException, DAOException{
        Connection c = null;
        PreparedStatement ps = null;

        boolean transactionState = false;
        try {
            c = conexao.getCon();
            transactionState = c.getAutoCommit();

            c.setAutoCommit(false);

            ps = c.prepareStatement(SQL_UPDATE_PACIENTE);
            ps.setString(1, paciente.getNomePaciente());
            ps.setString(2, paciente.getEndPaciente());
            ps.setString(3, paciente.getTelPaciente());
            ps.setString(4, paciente.getCpfPaciente());

            int result = ps.executeUpdate();
            if (result != 1) {
                throw new DAOException("PacienteDAO: update failed");
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
