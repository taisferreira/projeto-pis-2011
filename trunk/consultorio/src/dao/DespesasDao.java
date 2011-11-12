/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Despesa;

/**
 *
 * @author Fabricio
 */
public class DespesasDao {
    
    private static String SQL_CREATE_DESPESA = "insert into despesas"
            + " (DESCRICAO, VALOR, PAGO, CPF_USUARIO_FUNCIONARIO) "
            + " values((?),(?),(?),(?))";
    
    private Conexao conexao;
    
    public DespesasDao(Conexao con) {
        this.conexao = con;
    }
    
    public void cadDespesa(Despesa desp) throws DAOException, ClassNotFoundException{
        Connection c = null;
        PreparedStatement ps = null;

        boolean transactionState = false;
        try {
            c = conexao.getCon();
            transactionState = c.getAutoCommit();

            c.setAutoCommit(false);

            ps = c.prepareStatement(SQL_CREATE_DESPESA);
            ps.setString(1, desp.getDescricao());
            ps.setDouble(2, desp.getValor());
            ps.setString(3, desp.getPago());
            ps.setString(4, desp.getCpfUsuario());
            

            int result = ps.executeUpdate();
            if (result != 1) {
                throw new DAOException("ConvenioDAO: insert failed");
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
